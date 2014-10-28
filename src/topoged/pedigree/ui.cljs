(ns topoged.pedigree.ui
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))


(def model {"@I124153@" {:name "Antonia //", :id "@I124153@"}, "@I124144@" {:name "Julia //", :id "@I124144@"}, "@I124226@" {:name "Platia /Urgulanilla/", :id "@I124226@"}, "@I124149@" {:name "Gaius I /Antonius/", :id "@I124149@"}, "@I52792@" {:name "Scribonia //", :id "@I52792@"}, "@I52766@" {:parents ["@I52765@" "@I52764@"], :name "Tiberius Claudius /Nero/", :id "@I52766@"}, "@I124228@" {:parents ["@I124229@" nil], :name "Appius Claudius /Nero/", :id "@I124228@"}, "@I52768@" {:name "Vipsania /Agrippina/", :id "@I52768@"}, "@I52729@" {:name "Fulvia //", :id "@I52729@"}, "@I52731@" {:parents ["@I52730@" "@I52790@"], :name "Emperor Augustus /Octavian/", :id "@I52731@"}, "@I52727@" {:parents ["@I124148@" nil], :name "Creticus /Creticus/ Praetor of Rome", :id "@I52727@"}, "@I52788@" {:parents ["@I52787@" "@I52789@"], :name "Gaius /Marius/", :id "@I52788@"}, "@I124223@" {:name "Amelia Lepida //", :id "@I124223@"}, "@I52751@" {:name "Julia Aggripina /Caesar/", :id "@I52751@"}, "@I52794@" {:name "Valeria /Messalina/", :id "@I52794@"}, "@I52722@" {:parents ["@I52727@" nil], :name "Mark /Antony/ the Triumvir", :id "@I52722@"}, "@I124229@" {:name "Tiberius Claudius /Nero/", :id "@I124229@"}, "@I52784@" {:parents ["@I124162@" nil], :name "Gaius Julius /Caesar/", :id "@I52784@"}, "@I52733@" {:parents ["@I52784@" "@I52786@"], :name "Julius /Caesar/", :id "@I52733@"}, "@I52765@" {:parents ["@I124228@" nil], :name "Tiberius Claudius /Nero/ Pontiff of Rome", :id "@I52765@"}, "@I52787@" {:name "C /Marius/", :id "@I52787@"}, "@I124159@" {:parents ["@I124158@" nil], :name "Aufidia //", :id "@I124159@"}, "@I52789@" {:name "Fulcinia //", :id "@I52789@"}, "@I124162@" {:name "Gaius Julius I /Caesar/", :id "@I124162@"}, "@I52720@" {:parents ["@I52765@" "@I52764@"], :name "Claudius Drusus Nero /Germanicus/ Governor of Gaul", :id "@I52720@"}, "@I52793@" {:name "Marcus Livius Drusus /Claudianus/", :id "@I52793@"}, "@I52732@" {:name "Cleopatra VII // Queen of Egypt", :id "@I52732@"}, "@I52770@" {:name "Julia //", :id "@I52770@"}, "@I52728@" {:parents ["@I52727@" nil], :name "Lucius /Antonius/", :id "@I52728@"}, "@I52743@" {:name "Calpurnia //", :id "@I52743@"}, "@I52764@" {:parents ["@I52793@" "@I124159@"], :name "Liviia /Drusilla/", :id "@I52764@"}, "@I124158@" {:name "Marcus Aufidius /Lurco/", :id "@I124158@"}, "@I52730@" {:parents ["@I124150@" "@I52790@"], :name "Gaius /Octavianus/ Emperor Octavian", :id "@I52730@"}, "@I52721@" {:parents ["@I52722@" "@I52723@"], :name "Antonia /Minor/", :id "@I52721@"}, "@I124152@" {:name "Pulvia //", :id "@I124152@"}, "@I124225@" {:name "Aelia /Paetina/", :id "@I124225@"}, "@I3298@" {:name "Atius /Balbus/", :id "@I3298@"}, "@I52750@" {:name "Boudicca //", :id "@I52750@"}, "@I124169@" {:name "Gaius III /Octovius/", :id "@I124169@"}, "@I52791@" {:parents ["@I52784@" "@I52786@"], :name "Julia /Caesar/", :id "@I52791@"}, "@I124160@" {:name "Clodia /Pulchra/", :id "@I124160@"}, "@I52723@" {:parents ["@I124150@" "@I52790@"], :name "Octavia /Minor/", :id "@I52723@"}, "@I124150@" {:parents ["@I124169@" nil], :name "Gaius IV /Octavius/", :id "@I124150@"}, "@I52786@" {:parents ["@I52787@" "@I52789@"], :name "Aurelia //", :id "@I52786@"}, "@I52756@" {:name "Agrippina the elder //", :id "@I52756@"}, "@I52719@" {:parents ["@I52720@" "@I52721@"], :name "Tiberius Claudius Caesar Augustus Germanicus /Claudius/ Emperor of Rome", :id "@I52719@"}, "@I124148@" {:parents ["@I124149@" nil], :name "Marcus Antonius I \"The Orator\" // Praetor of Rome", :id "@I124148@"}, "@I52783@" {:parents ["@I52720@" "@I52721@"], :name "Julius Caesar /Germanicus/", :id "@I52783@"}, "@I52790@" {:parents ["@I3298@" "@I52791@"], :name "Atia /Caesar/", :id "@I52790@"}})

(def app-state (atom {:model model
                      :names 
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
        (map  (fn [[text value]] (dom/option #js {:value value} text)) (:names app)))))
  app-state
  {:target (. js/document (getElementById "names"))})


(defn hamster []
  (+ 1 2 3))


