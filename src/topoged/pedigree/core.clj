(ns topoged.pedigree.core
  (:require [topoged.gedcom :refer [gedcom-seq get-value-at]]
            [clojure.java.io :refer [file reader]]))


(defn add-parents [result rec]
  (let [father (first (get-value-at rec [:FAM :HUSB]))
        mother (first (get-value-at rec [:FAM :WIFE]))
        children  (get-value-at rec [:FAM :CHIL])]
    (if (seq children)
      (reduce #(assoc-in %1 [%2 :parents] [father mother]) result children)
      result)))


(defn add-name [result rec]
  (let [f (juxt #(get-value-at % [:INDI])
                #(get-value-at % [:INDI :NAME]))
        [id name] (map first (f rec))]
    (if id 
      (-> result
      (assoc-in  [id :id] id)
      (assoc-in  [id :name] name))
      result)))

(defn process-record [result rec]
  (-> result
      (add-name  rec)
      (add-parents rec)))

(defn read-lines [result lines]
  (->> (gedcom-seq lines)
       (reduce process-record result)))

(defn gedcom-file [f]
  (with-open [rdr (reader f)]
    (read-lines {} (line-seq rdr))))

;;  (ggg  (line-seq (reader "test-resources/TiberiusClaudiusCaesarAugustusGermanicusClaudiusEmperorofRome.ged"))))


