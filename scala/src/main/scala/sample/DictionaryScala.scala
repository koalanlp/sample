package sample

import kr.bydelta.koala.POS
import kr.bydelta.koala.kmr.Dictionary
//import kr.bydelta.koala.eunjeon.Dictionary
//import kr.bydelta.koala.hnn.Dictionary
//import kr.bydelta.koala.kkma.Dictionary
//import kr.bydelta.koala.okt.Dictionary

import kr.bydelta.koala.Implicits._

object DictionaryScala {
  def main(args: Array[String]): Unit = {
    Dictionary.INSTANCE.addUserDictionary("하동균" -> POS.NNP)
    Dictionary.INSTANCE.addUserDictionary("나비야" -> POS.NNP)

    println(Dictionary.INSTANCE.contains("하동균", Set(POS.NNP, POS.NNG)))
    println(Dictionary.INSTANCE.contains("하동균" -> POS.NNP))
    println(Dictionary.INSTANCE.getNotExists(true, "하동균" -> POS.NNP).mkString)

    println("# 접사 목록")
    val it = Dictionary.INSTANCE.getBaseEntries(_.isAffix)
    while(it.hasNext){
      val word = it.next()
      println(s"${word._1} (Tag=${word._2})")
    }

    println("# 사용자 사전 목록")
    Dictionary.INSTANCE.getItems.foreach{
      it =>
        println(s"${it._1} (Tag=${it._2})")
    }

    println("---- 타 분석기 사전 불러오기 ----")
    Dictionary.INSTANCE.importFrom(kr.bydelta.koala.kkma.Dictionary.INSTANCE, false, _.isNoun)
    println("# 사용자 사전 목록 (30개)")
    Dictionary.INSTANCE.getItems.take(30).foreach {
      it =>
        println(s"${it._1} (Tag=${it._2})")
    }
  }
}

