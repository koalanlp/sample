package sample

import java.io.{BufferedReader, InputStreamReader}
import java.util
import java.util.List

import kr.bydelta.koala.data._
import kr.bydelta.koala.etri.RoleLabeler

import scala.io.StdIn
//import kr.bydelta.koala.etri.EntityRecognizer
//import kr.bydelta.koala.etri.Parser
//import kr.bydelta.koala.etri.Tagger

import kr.bydelta.koala.proc.CanAnalyzeProperty
import kr.bydelta.koala.Implicits._

object ETRIApiScala {
  def main(args: Array[String]): Unit = {
    val API_KEY =
      if (args.length > 0) args(0)
      else System.getenv("API_KEY")

    if (API_KEY == null) {
      println("ETRI 분석기는 API 키 설정이 필요합니다. API_KEY 환경변수를 설정하거나 프로그램 인자로 key를 전달해주세요.")
      System.exit(1)
    }

    val labeler = new RoleLabeler(API_KEY)
    //    val recognizer = new EntityRecognizer(API_KEY);
    //    val parser = new Parser(API_KEY);
    //    val tagger = new Tagger(API_KEY);

    actor(labeler)
  }

  def actor(labeler: CanAnalyzeProperty[_]): Unit = {
    print("분석할 문장을 입력하세요>> ")

    val text = StdIn.readLine()

    if (text == null || text.isEmpty)
      return

    val sentences = labeler(text)

    for (sent <- sentences) {
      println("===== Sentence =====")
      println(sent.singleLineString)

      val entities = sent.getEntities
      if (entities != null) {
        println("# Named Entities")

        for (entity <- entities)
          println(s"[${entity.getSurface}]는 ${entity.getFineLabel} 유형의 개체명으로, 형태소 [${entity.mkString(" ")}]를 포함합니다.")
      }

      val dependencies = sent.getDependencies
      if (dependencies != null) {
        println("# Dependency Parse")

        for (edge <- dependencies)
          println(s"[${edge.getDependent.getSurface}]는 [${
            Option(edge.getGovernor).map(_.getSurface).getOrElse("ROOT")
          }]의 ${edge.getType}-${edge.getDepType}")
      }

      val roles = sent.getRoles
      if (roles != null) {
        println("# Role Labeling")

        for (edge <- roles)
          println(s"[${edge.getArgument.getSurface}]는 [${edge.getPredicate.getSurface}]의 ${edge.getLabel}")
      }
    }

    actor(labeler)
  }
}
