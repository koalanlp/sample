package sample

import kr.bydelta.koala.okt.SentenceSplitter
//import kr.bydelta.koala.hnn.SentenceSplitter

import kr.bydelta.koala.Implicits._

import scala.io.StdIn

object SentenceSplitScala {
  val splitter: SentenceSplitter = new SentenceSplitter

  def actor(): Unit = {
    print("분석할 문장을 입력하세요>> ")

    val text = StdIn.readLine()

    if (text == null || text.trim.isEmpty)
      return

    val sentences: Seq[String] = splitter(text)

    println("===== Sentence =====")
    sentences.zipWithIndex.foreach{
      case (sent, i) =>
        println(s"[$i] $sent")
    }

    actor()
  }

  def main(args: Array[String]): Unit = {
    actor()
  }
}
