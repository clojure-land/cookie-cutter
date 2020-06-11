# lambdaisland/cookie-cutter

Auto-generate Clojure test namespaces in bulk. Useful for testing test tooling.

Main namespace: `cookie-cutter.core`, takes prefix (test directory), suffix
(clj/cljs/cljc), and number of test files to generate.

```
clj -Sdeps '{:deps {lambdaisland/cookie-cutter {:local/root "/home/arne/github/lambdaisland/cookie-cutter"}}}' -m cookie-cutter.core test cljs 5
```

```
$ tree test
test
├── dg
│   └── kaqw
│       └── pu_test.cljs
├── eohq
│   └── zfbspnh
│       └── rv_n
│           └── vhn_mhw
│               └── nsg_test.cljs
├── esnudhq
│   └── tpp_test.cljs
├── qcpyn_s
│   └── msj_test.cljs
└── um
    └── zjyg_f_test.cljs

9 directories, 5 files
```

```
$ cat test/eohq/zfbspnh/rv_n/vhn_mhw/nsg_test.cljs
(ns
 eohq.zfbspnh.rv-n.vhn-mhw.nsg-test
 (:require
  [clojure.test :as t :refer [deftest testing is are use-fixtures]]))

(deftest
 as-test
 (is (= {:Z "", :c-? :h} {:Z "", :c-? :h}))
 (is
  (=
   {:V** :Oi6, :-c 3, :_+9 -3, :O6 "", :d "Î"}
   {:V** :Oi6, :-c 3, :_+9 -3, :O6 "", :d "Î"}))
 (is (= {:J_V -3, :e0 0, :* -3, :/ -5} {:J_V -3, :e0 0, :* -3, :/ -5}))
 (is (= {:.u "x", :H :GF?, :?0n "Q3"} {:.u "x", :H :GF?, :?0n "  Q3"})))

(deftest
 uddoq-test
 (is
  (=
   {:C7! :K7T, :sd -1, :. "/6", :j "", :.9 2}
   {:C7! :K7T, :sd -1, :. "/6", :j "", :.9 2})))

(deftest
 oq-test
 (is
  (=
   {:Z :sKa, :j -2, :KM "", :p! :Y}
   {:Z :sKa, :j -2, :KM "", :p! :Y}))
 (is (= {:*t_ 4, :GN6 :zR} {:*t_ 4, :GN6 :zR}))
 (is (= {:K :HV+} {:K :HV+})))
```

## License

Copyright &copy; 2020 Arne Brasseur and Contributors

Licensed under the term of the Mozilla Public License 2.0, see LICENSE.
