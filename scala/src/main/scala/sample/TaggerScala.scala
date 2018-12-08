package sample

import kr.bydelta.koala.data.Sentence
//import kr.bydelta.koala.eunjeon.Tagger
// import kr.bydelta.koala.kmr.Tagger
// import kr.bydelta.koala.etri.Tagger
// import kr.bydelta.koala.eunjeon.Tagger
 import kr.bydelta.koala.arirang.Tagger
// import kr.bydelta.koala.daon.Tagger
// import kr.bydelta.koala.rhino.Tagger
// import kr.bydelta.koala.okt.Tagger
// import kr.bydelta.koala.kkma.Tagger
// import kr.bydelta.koala.hnn.Tagger

import scala.io.StdIn
import kr.bydelta.koala.Implicits._

object TaggerScala {
  val tagger: Tagger = new Tagger

  def actor(): Unit = {
    print("분석할 문장을 입력하세요>> ")

    val text = StdIn.readLine()

    if (text == null || text.trim.isEmpty)
      return

    val sentences: Seq[Sentence] = tagger(text)

    println("===== Sentence =====")
    sentences.zipWithIndex.foreach{
      case (sent, i) =>
        println("===== Sentence #$i =====")
        println(sent.surfaceString())

        println("# Analysis Result")
        // println(sent.singleLineString())

        for(word <- sent){
          print(s"Word [${word.getId}] ${word.getSurface} = ")

          for(morph <- word){
            print(s"${morph.getSurface}/${morph.getTag} ")
          }
          println()
        }
    }

    actor()
  }

  def main(args: Array[String]): Unit = {
    actor()
  }
}
