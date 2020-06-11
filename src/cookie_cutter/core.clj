(ns cookie-cutter.core
  (:gen-class)
  (:require [backtick :as bt :refer [template]]
            [clojure.test.check.generators :as gen]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [clojure.pprint :as pprint]))

(def alpha-lower
  (gen/fmap char (gen/choose 97 122)))

(def alpha-lower-dash
  (gen/frequency [[7 alpha-lower]
                  [1 (gen/return \-)]]))

(def ns-part
  (gen/fmap (fn [[i m e]]
              (str i (apply str m) e))
            (gen/tuple alpha-lower
                       (gen/vector alpha-lower-dash)
                       alpha-lower)))

(def ns-parts
  (gen/fmap (fn [parts]
              (concat (butlast parts)
                      [(symbol (str (last parts) "-test"))]))
            (gen/sized (fn [i] (gen/vector ns-part 2 (max 2 i))))))

(defn ns-name-join [parts]
  (symbol (str/join "." parts)))

(defn ns-path [parts suffix]
  (str/replace (str (str/join "/" parts) "." suffix) #"-" "_"))

(defn ns-decl [name]
  (backtick/template
   (ns ~name
     (:require [clojure.test :as t :refer [deftest testing is are use-fixtures]]))))

(def passing-assertion
  (gen/fmap #(list 'is (list '= % %))
            (gen/map gen/keyword (gen/one-of [gen/string
                                              gen/keyword
                                              gen/small-integer]))))

(def failing-assertion
  (gen/fmap #(list 'is (list '= (first %) (second %)))
            (gen/such-that
             (fn [[x y]] (not= x y))
             (gen/tuple
              (gen/map gen/keyword (gen/one-of [gen/string
                                                gen/keyword
                                                gen/small-integer]))
              (gen/map gen/keyword (gen/one-of [gen/string
                                                gen/keyword
                                                gen/small-integer]))))))

(def assertion
  (gen/frequency [[8 passing-assertion]
                  [1 failing-assertion]]))

(defn test-impl [name assertions]
  (backtick/template
   (deftest ~name
     ~@assertions)))

(defn make-test-ns [prefix suffix]
  (let [ns-name-size 5
        test-num-size 5
        assert-num-size 5
        [ns-name path] (gen/generate (gen/fmap (juxt ns-name-join #(ns-path % suffix)) ns-parts) ns-name-size)
        path (str prefix "/" path)]
    (io/make-parents path)
    (with-open [f (io/writer (io/file path))]
      (binding [*out* f]
        (pprint/pprint (ns-decl ns-name))
        (println)
        (doseq [test-name (set (gen/generate (gen/list (gen/fmap #(symbol (str % "-test")) ns-part)) test-num-size))]
          (pprint/pprint (test-impl
                          test-name
                          (gen/generate (gen/list assertion) assert-num-size)))
          (println))))))

(defn -main [prefix suffix count]
  (dotimes [_ (Integer. count)]
    (make-test-ns prefix suffix)))
