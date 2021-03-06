(ns topoged.pedigree.ui
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [topoged.pedigree.core :refer [gedcom-file parents-of names bf-tree-seq]]))



(def generations 5)

(def powers-of-2 [1 2 4 8 16 32 64 128 256 512 1024 2048])

(defn powers-of-2-desc [i]
  (vec (reverse (take i powers-of-2))))

(defn partition-with-offset [coll [offset & offset-coll]]
  (lazy-seq
   (if (seq offset-coll)
     (cons (take offset coll) (partition-with-offset (drop offset coll) offset-coll))
     (cons (take offset coll) nil))))

(def model {"@I124153@" {:name "Antonia //", :id "@I124153@"}, "@I124144@" {:name "Julia //", :id "@I124144@"}, "@I124226@" {:name "Platia /Urgulanilla/", :id "@I124226@"}, "@I124149@" {:name "Gaius I /Antonius/", :id "@I124149@"}, "@I52792@" {:name "Scribonia //", :id "@I52792@"}, "@I52766@" {:parents ["@I52765@" "@I52764@"], :name "Tiberius Claudius /Nero/", :id "@I52766@"}, "@I124228@" {:parents ["@I124229@" nil], :name "Appius Claudius /Nero/", :id "@I124228@"}, "@I52768@" {:name "Vipsania /Agrippina/", :id "@I52768@"}, "@I52729@" {:name "Fulvia //", :id "@I52729@"}, "@I52731@" {:parents ["@I52730@" "@I52790@"], :name "Emperor Augustus /Octavian/", :id "@I52731@"}, "@I52727@" {:parents ["@I124148@" nil], :name "Creticus /Creticus/ Praetor of Rome", :id "@I52727@"}, "@I52788@" {:parents ["@I52787@" "@I52789@"], :name "Gaius /Marius/", :id "@I52788@"}, "@I124223@" {:name "Amelia Lepida //", :id "@I124223@"}, "@I52751@" {:name "Julia Aggripina /Caesar/", :id "@I52751@"}, "@I52794@" {:name "Valeria /Messalina/", :id "@I52794@"}, "@I52722@" {:parents ["@I52727@" nil], :name "Mark /Antony/ the Triumvir", :id "@I52722@"}, "@I124229@" {:name "Tiberius Claudius /Nero/", :id "@I124229@"}, "@I52784@" {:parents ["@I124162@" nil], :name "Gaius Julius /Caesar/", :id "@I52784@"}, "@I52733@" {:parents ["@I52784@" "@I52786@"], :name "Julius /Caesar/", :id "@I52733@"}, "@I52765@" {:parents ["@I124228@" nil], :name "Tiberius Claudius /Nero/ Pontiff of Rome", :id "@I52765@"}, "@I52787@" {:name "C /Marius/", :id "@I52787@"}, "@I124159@" {:parents ["@I124158@" nil], :name "Aufidia //", :id "@I124159@"}, "@I52789@" {:name "Fulcinia //", :id "@I52789@"}, "@I124162@" {:name "Gaius Julius I /Caesar/", :id "@I124162@"}, "@I52720@" {:parents ["@I52765@" "@I52764@"], :name "Claudius Drusus Nero /Germanicus/ Governor of Gaul", :id "@I52720@"}, "@I52793@" {:name "Marcus Livius Drusus /Claudianus/", :id "@I52793@"}, "@I52732@" {:name "Cleopatra VII // Queen of Egypt", :id "@I52732@"}, "@I52770@" {:name "Julia //", :id "@I52770@"}, "@I52728@" {:parents ["@I52727@" nil], :name "Lucius /Antonius/", :id "@I52728@"}, "@I52743@" {:name "Calpurnia //", :id "@I52743@"}, "@I52764@" {:parents ["@I52793@" "@I124159@"], :name "Liviia /Drusilla/", :id "@I52764@"}, "@I124158@" {:name "Marcus Aufidius /Lurco/", :id "@I124158@"}, "@I52730@" {:parents ["@I124150@" "@I52790@"], :name "Gaius /Octavianus/ Emperor Octavian", :id "@I52730@"}, "@I52721@" {:parents ["@I52722@" "@I52723@"], :name "Antonia /Minor/", :id "@I52721@"}, "@I124152@" {:name "Pulvia //", :id "@I124152@"}, "@I124225@" {:name "Aelia /Paetina/", :id "@I124225@"}, "@I3298@" {:name "Atius /Balbus/", :id "@I3298@"}, "@I52750@" {:name "Boudicca //", :id "@I52750@"}, "@I124169@" {:name "Gaius III /Octovius/", :id "@I124169@"}, "@I52791@" {:parents ["@I52784@" "@I52786@"], :name "Julia /Caesar/", :id "@I52791@"}, "@I124160@" {:name "Clodia /Pulchra/", :id "@I124160@"}, "@I52723@" {:parents ["@I124150@" "@I52790@"], :name "Octavia /Minor/", :id "@I52723@"}, "@I124150@" {:parents ["@I124169@" nil], :name "Gaius IV /Octavius/", :id "@I124150@"}, "@I52786@" {:parents ["@I52787@" "@I52789@"], :name "Aurelia //", :id "@I52786@"}, "@I52756@" {:name "Agrippina the elder //", :id "@I52756@"}, "@I52719@" {:parents ["@I52720@" "@I52721@"], :name "Tiberius Claudius Caesar Augustus Germanicus /Claudius/ Emperor of Rome", :id "@I52719@"}, "@I124148@" {:parents ["@I124149@" nil], :name "Marcus Antonius I \"The Orator\" // Praetor of Rome", :id "@I124148@"}, "@I52783@" {:parents ["@I52720@" "@I52721@"], :name "Julius Caesar /Germanicus/", :id "@I52783@"}, "@I52790@" {:parents ["@I3298@" "@I52791@"], :name "Atia /Caesar/", :id "@I52790@"}})

(def app-state (atom {:model model
                      :selected (ffirst model)
}))



(defn jj [m]
  (let [id (.. m -target -value )]
    (swap! app-state assoc :selected id))) 

(defn select-person [id]
    (swap! app-state assoc :selected id))

(defn import-gedcom [ged]
  (let [model (gedcom-file ged)]
    (swap! app-state assoc :model model)))
  
(defn open-gedcom [f]
  (.log js/console f)
  (.log js/console (.. f -target -files))
  (let [file (aget (.. f -target -files) 0)
        reader (js/FileReader.)]
     (set! (.-onload reader) 
           #(.log js/console (import-gedcom (.. % -target -result))))
     (.readAsText reader file)))

(defn get-name [model id]
  (let [rtnval (get model id)]
    (.log js/console (str "id:" id))
    (.log js/console rtnval)
    (if rtnval (:name rtnval) "UNKNOWN")))

(defn id? [id]
  (not (= id :unknown)))


(defn ancestor-seq [{:keys [selected model] :as app}]
  (when selected
    (bf-tree-seq (partial parents-of model) (partial parents-of model) selected)))


(defn rows [data]
  (let [elements (map vector (ancestor-seq data) (concat ["self"] (cycle ["M" "F"])))]
    (map vector 
         (partition-with-offset elements  powers-of-2)
         powers-of-2
         (powers-of-2-desc generations))))

(.log js/console (str (nth (rows @app-state) 0)))

(defn td [id]
  (when (id? id) (select-person id)))

(defn widget [{:keys [model] :as data} owner]
  (om/component 
   (apply dom/table nil
          (for [[elements gen rev-gen] (rows data)]
            (apply dom/tr nil
                   (map (fn [[id classname]] (dom/td #js {:onClick #(td id) :colSpan rev-gen :className classname}  
                                         (get-name model id))) 
                        elements))))))


(defn gedcom-selector [data owner]
  (om/component
   (dom/input #js {:type "file" :id "input" :onChange open-gedcom})))

(om/root (fn [data owner]
           (om/component
            (apply dom/ul nil
                   (map (fn [ele] (dom/li #js {:onClick #(select-person  (first ele))} (str ele))) (:model data))))) 

         app-state
         {:target (. js/document (getElementById "message"))})

(om/root widget app-state
  {:target (. js/document (getElementById "tree"))})

(om/root gedcom-selector {:text "Gedcom!"}
  {:target (. js/document (getElementById "gedcom"))})



(om/root
  (fn [app owner]
    (let [n (names (:model app))]
      (om/component 
       (apply dom/select #js {:onChange jj}
              (map  (fn [[text value]] (dom/option #js {:value value :key value} text)) n)))))
    app-state
    {:target (. js/document (getElementById "names"))})



(defn hamster []
  (+ 1 2 3))


