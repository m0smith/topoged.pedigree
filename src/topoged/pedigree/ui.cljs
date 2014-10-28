(ns topoged.pedigree.ui
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))


(def app-state (atom {:list 
                      [["Antonia //" "@I124153@"]
                       ["Julia //" "@I124144@"] 
                       ["Platia /Urgulanilla/" "@I124226@"] 
                       ["Gaius I /Antonius/" "@I124149@"] 
                       ["Scribonia //" "@I52792@"] 
                       ["Tiberius Claudius /Nero/" "@I52766@"] 
                       ["Appius Claudius /Nero/" "@I124228@"] 
                       ["Vipsania /Agrippina/" "@I52768@"] 
                       ["Fulvia //" "@I52729@"] 
                       ["Emperor Augustus /Octavian/" "@I52731@"] 
                       ["Creticus /Creticus/ Praetor of Rome" "@I52727@"] 
                       ["Gaius /Marius/" "@I52788@"] 
                       ["Amelia Lepida //" "@I124223@"] 
                       ["Julia Aggripina /Caesar/" "@I52751@"] 
                       ["Valeria /Messalina/" "@I52794@"] 
                       ["Mark /Antony/ the Triumvir" "@I52722@"] 
                       ["Tiberius Claudius /Nero/" "@I124229@"] 
                       ["Gaius Julius /Caesar/" "@I52784@"] 
                       ["Julius /Caesar/" "@I52733@"] 
                       ["Tiberius Claudius /Nero/ Pontiff of Rome" "@I52765@"] 
                       ["C /Marius/" "@I52787@"] 
                       ["Aufidia //" "@I124159@"] 
                       ["Fulcinia //" "@I52789@"] 
                       ["Gaius Julius I /Caesar/" "@I124162@"] 
                       ["Claudius Drusus Nero /Germanicus/ Governor of Gaul" "@I52720@"] 
                       ["Marcus Livius Drusus /Claudianus/" "@I52793@"] 
                       ["Cleopatra VII // Queen of Egypt" "@I52732@"] 
                       ["Julia //" "@I52770@"] 
                       ["Lucius /Antonius/" "@I52728@"] 
                       ["Calpurnia //" "@I52743@"] 
                       ["Liviia /Drusilla/" "@I52764@"] 
                       ["Marcus Aufidius /Lurco/" "@I124158@"] 
                       ["Gaius /Octavianus/ Emperor Octavian" "@I52730@"] 
                       ["Antonia /Minor/" "@I52721@"] 
                       ["Pulvia //" "@I124152@"] 
                       ["Aelia /Paetina/" "@I124225@"] 
                       ["Atius /Balbus/" "@I3298@"] 
                       ["Boudicca //" "@I52750@"] 
                       ["Gaius III /Octovius/" "@I124169@"] 
                       ["Julia /Caesar/" "@I52791@"] 
                       ["Clodia /Pulchra/" "@I124160@"] 
                       ["Octavia /Minor/" "@I52723@"] 
                       ["Gaius IV /Octavius/" "@I124150@"] 
                       ["Aurelia //" "@I52786@"] 
                       ["Agrippina the elder //" "@I52756@"] 
                       ["Tiberius Claudius Caesar Augustus Germanicus /Claudius/ Emperor of Rome" "@I52719@"] 
                       ["Marcus Antonius I \"The Orator\" // Praetor of Rome" "@I124148@"] 
                       ["Julius Caesar /Germanicus/" "@I52783@"] 
                       ["Atia /Caesar/" "@I52790@"]]}))



(defn widget [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/h1 nil (:text data)))))

(om/root widget {:text "Behold the tree!"}
  {:target (. js/document (getElementById "tree"))})

(om/root
  (fn [app owner]
    (om/component 
      (apply dom/select nil
        (map  (fn [[text value]] (dom/option #js {:value value} text)) (:list app)))))
  app-state
  {:target (. js/document (getElementById "names"))})


(defn hamster []
  (+ 1 2 3))


