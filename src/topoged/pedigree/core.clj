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

(defn gedcom-file 
  "Read a GEDCOM and output the PedigreeModel"
  [f]
  (with-open [rdr (reader f)]
    (read-lines {} (line-seq rdr))))

(defn names [model]
  (map (juxt :name :id) (vals model)))


(defn parents-of [model id]
  (condp = id
    nil [:unknown :unknown]
    :unknown [:unknown :unknown]
    (let [rtnval (if id
                   (->> (model id)
                        :parents))]
      (or rtnval [:unknown :unknown]))))

(defn bf-tree-seq [branch? children tree]
  ((fn step [queue]
     ;(println "<<" (seq queue) ">>")

     (lazy-seq
      (when (seq queue)
        (let [node (peek queue)]
          ;(println "node:"  node "branch?" (branch? node))
          (if (branch? node)
            (cons node (step (into (pop queue) (children node))))
            (cons node (step (pop queue))))))))
   (conj cljs.core.PersistentQueue.EMPTY tree)))

(defn generation [ns]
  (concat (map (partial str "F") ns) (map (partial str "M") ns)))
  

; (def model (gedcom-file "test-resources/TiberiusClaudiusCaesarAugustusGermanicusClaudiusEmperorofRome.ged"))
; (defn xx [x] (map vector (mapcat identity (take 8 (iterate generation [""]))) (bf-tree-seq (partial parents-of model) (partial parents-of model) x)))
; (filter #(!= :unknown (last (last %))) (map xx (map second (names model))))


;  (ggg  (line-seq (reader "test-resources/TiberiusClaudiusCaesarAugustusGermanicusClaudiusEmperorofRome.ged"))))


