package sample

import kr.bydelta.koala.Implicits._
import kr.bydelta.koala.data.{DepEdge, Sentence}
import kr.bydelta.koala.kkma.Parser
// import kr.bydelta.koala.etri.Parser
// import kr.bydelta.koala.hnn.Tagger

import scala.io.StdIn

object DepParserScala {
  val parser: Parser = new Parser

  def actor(): Unit = {
    print("분석할 문장을 입력하세요>> ")

    val text = StdIn.readLine()

    if (text == null || text.trim.isEmpty)
      return

    val sentences: Seq[Sentence] = parser(text)

    for (sent <- sentences) {
      println("===== Sentence =====")
      println(sent.singleLineString)
      println("# Dependency Parse result")

      val dependencies: Seq[DepEdge] = sent.getDependencies
      if (dependencies != null)
        for (edge <- dependencies)
          println(s"[${edge.getDependent.getSurface}]는 [${
            Option(edge.getGovernor).map(_.getSurface).getOrElse("ROOT")}]의 ${edge.getType}-${edge.getDepType}")
      else
        println("(Unexpected) NULL!")
    }

    actor()
  }

  def main(args: Array[String]): Unit = {
    actor()
  }
}
