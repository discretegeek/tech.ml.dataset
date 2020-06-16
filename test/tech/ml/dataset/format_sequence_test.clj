(ns tech.ml.dataset.format-sequence-test
  (:require [tech.ml.dataset.format-sequence :refer [format-sequence]]
            [clojure.test :refer [deftest is]]))


(def a [0.000001 0.00001 0.0001 0.001 0.01 0.1 0.0
        1.0 10.0 100.0 1000.0 10000.0 100000.0])
(def b [10.0 10.1 10.11 10.111 10.1111 10.11111
        1.0 1.1 1.11 1.111 1.1111 1.11111
        0.0 0.1 0.11 0.111 0.1111 -0.11111])
(def c (range -5 4 0.8795833))
(def d [-1.0e-20 -1.334e-100 3.43e100 4.556e20
        1.0e-20 1.334e-100 -3.43e100 -41.556e20
        0.999e-300 -0.999e300])
(def e [-1.0e99 1.0e99])
(def f [-1.0e100 1.0e100])
(def g [0.002 0.0002 0.000333 0.1 -0.0003 0.0])
(def h [0.002 0.0002 0.00333 0.00001 -0.0003 0.022 0.0001])
(def i [10.0 ##NaN ##Inf ##-Inf 100 0.001 nil])
(def j (map float [39.81 36.35 43.22 28.37 25.45
                   -39.81 36.351 43.221 28.371 25.451]))

(deftest regression-tests
  (is (= (format-sequence j)
         '(" 39.810" " 36.350" " 43.220" " 28.370" " 25.450" "-39.810" " 36.351" " 43.221" " 28.371" " 25.451")))
  (is (= (format-sequence i 0 0)
         '("1.0E+01" "    NaN" "    Inf" "   -Inf" "1.0E+02" "1.0E-03" "    NaN")))
  (is (= (format-sequence a)
         '("     0.000001" "     0.000010" "     0.000100" "     0.001000" "     0.010000" "     0.100000" "     0.000000" "     1.000000" "    10.000000" "   100.000000" "  1000.000000" " 10000.000000" "100000.000000")))
  (is (= (format-sequence a 5 4)
         '("1.0E-06" "1.0E-05" "1.0E-04" "1.0E-03" "1.0E-02" "1.0E-01" "0.0E+00" "1.0E+00" "1.0E+01" "1.0E+02" "1.0E+03" "1.0E+04" "1.0E+05")))
  (is (= (format-sequence b)
         '("10.00000" "10.10000" "10.11000" "10.11100" "10.11110" "10.11111" " 1.00000" " 1.10000" " 1.11000" " 1.11100" " 1.11110" " 1.11111" " 0.00000" " 0.10000" " 0.11000" " 0.11100" " 0.11110" "-0.11111")))
  (is (= (format-sequence b 5 2)
         '(" 1.00000E+01" " 1.01000E+01" " 1.01100E+01" " 1.01110E+01" " 1.01111E+01" " 1.01111E+01" " 1.00000E+00" " 1.10000E+00" " 1.11000E+00" " 1.11100E+00" " 1.11110E+00" " 1.11111E+00" " 0.00000E+00" " 1.00000E-01" " 1.10000E-01" " 1.11000E-01" " 1.11100E-01" "-1.11110E-01")))
  (is (= (format-sequence c)
         '("-5.0000000" "-4.1204167" "-3.2408334" "-2.3612501" "-1.4816668" "-0.6020835" " 0.2774998" " 1.1570831" " 2.0366664" " 2.9162497" " 3.7958330")))
  (is (= (format-sequence c 4)
         '("-5.0000" "-4.1204" "-3.2408" "-2.3613" "-1.4817" "-0.6021" " 0.2775" " 1.1571" " 2.0367" " 2.9162" " 3.7958")))
  (is (= (format-sequence c 4 0)
         '("-5.0000E+00" "-4.1204E+00" "-3.2408E+00" "-2.3613E+00" "-1.4817E+00" "-6.0208E-01" " 2.7750E-01" " 1.1571E+00" " 2.0367E+00" " 2.9162E+00" " 3.7958E+00")))
  (is (= (format-sequence d)
         '("-1.0000E-020" "-1.3340E-100" " 3.4300E+100" " 4.5560E+020" " 1.0000E-020" " 1.3340E-100" "-3.4300E+100" "-4.1556E+021" " 9.9900E-301" "-9.9900E+299")))
  (is (= (format-sequence e)
         '("-1.0E+99" " 1.0E+99")))
  (is (= (format-sequence f)
         '("-1.0E+100" " 1.0E+100")))
  (is (= (format-sequence g)
         '(" 0.002000" " 0.000200" " 0.000333" " 0.100000" "-0.000300" " 0.000000")))
  (is (= (format-sequence h)
         '(" 0.00200" " 0.00020" " 0.00333" " 0.00001" "-0.00030" " 0.02200" " 0.00010")))
  (is (= (format-sequence i)
         '(" 10.000" "    NaN" "    Inf" "   -Inf" "100.000" "  0.001" "    NaN")))
  (is (= (format-sequence i 0 0)
         '("1.0E+01" "    NaN" "    Inf" "   -Inf" "1.0E+02" "1.0E-03" "    NaN")))
  (is (= (format-sequence j)
         '(" 39.810" " 36.350" " 43.220" " 28.370" " 25.450" "-39.810" " 36.351" " 43.221" " 28.371" " 25.451"))))
