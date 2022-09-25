(ns build-shared
  "a few things that are both needed by bb script code and build.clj"
  (:require [clojure.string :as string]
            [clojure.edn :as edn]))

(defn- project-info []
  (-> (edn/read-string (slurp "deps.edn"))
      :aliases :neil :project))

(def version-tag-prefix "v")

(defn lib-version []
  (-> (project-info) :version))

(defn lib-artifact-name []
  (-> (project-info) :name))

(defn version->tag [version]
  (str version-tag-prefix version))

(defn tag->version [ci-tag]
  (and (string/starts-with? ci-tag version-tag-prefix)
       (string/replace-first ci-tag version-tag-prefix "")))
