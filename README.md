# lambdaisland/cookie-cutter

Auto-generate Clojure test namespaces in bulk. Useful for testing test tooling.

Main namespace: `cookie-cutter.core`, takes prefix (test directory), suffix
(clj/cljs/cljc), and number of test files to generate.

```
clj -Sdeps '{:deps {lambdaisland/cookie-cutter {:local/root "/home/arne/github/lambdaisland/cookie-cutter"}}}' -m cookie-cutter.core test cljs 20
```

## License

Copyright &copy; 2020 Arne Brasseur and Contributors

Licensed under the term of the Mozilla Public License 2.0, see LICENSE.
