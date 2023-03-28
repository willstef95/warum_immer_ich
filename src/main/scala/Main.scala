@main def hello(): Unit =
  println("Herzlich Willkommen bei dem Spiel wii")
  print(ausgaben())

val eol = sys.props("line.separator")
def PgrenzeO_U(b: Int = 2) = "---------" * b + eol
def PgrenzeL_R(b: Int = 2) = "|       |"
def PgrenzeL_RZ(b: Int = 2) = "|   " + zahl() + "   |"
def PgrenzeL_RO(b: Int = 245) = "|   O   |"
def feldLeer(b: Int = 2) = PgrenzeL_R() * b + eol
def feldO(b: Int = 2) = PgrenzeL_RO() * b + eol
def feldX(b: Int = 2) = PgrenzeL_RZ() * b + eol
def zahl(b: Int = 0) = "x"
def feld(b: Int = 2) = feldLeer(b) + feldO(b) + feldX(b) + feldLeer(b)
def ausgaben(b: Int = 20) =
  ((PgrenzeO_U(b) + feld(b)) * b + PgrenzeO_U(b))
