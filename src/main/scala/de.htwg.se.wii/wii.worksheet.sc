def translateW(gewurfelt: Int) = {
  val x = gewurfelt % (size)
  val y =
    if (gewurfelt % (size - 1) == 0) gewurfelt / size
    else gewurfelt / size
  (x, y)
}

val size = 4

println(translateW(0))
println(translateW(1))
println(translateW(2))
println(translateW(3))
println(translateW(4))
println(translateW(5))
println(translateW(6))
println(translateW(7))
println(translateW(8))
println(translateW(9))
println(translateW(10))
println(translateW(11))
println(translateW(12))
println(translateW(13))
println(translateW(14))
println(translateW(15))
