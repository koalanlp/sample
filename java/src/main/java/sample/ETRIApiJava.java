package sample;

import kr.bydelta.koala.data.*;
import kr.bydelta.koala.etri.RoleLabeler;
//import kr.bydelta.koala.etri.EntityRecognizer;
//import kr.bydelta.koala.etri.Parser;
//import kr.bydelta.koala.etri.Tagger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;


public class ETRIApiJava {
    public static void main(String[] args) {
        String API_KEY;
        if (args.length > 0)
            API_KEY = args[0];
        else
            API_KEY = System.getenv("API_KEY");

        if (API_KEY == null) {
            System.out.println("ETRI 분석기는 API 키 설정이 필요합니다. " +
                    "API_KEY 환경변수를 설정하거나 프로그램 인자로 key를 전달해주세요.");
            System.exit(1);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        RoleLabeler labeler = new RoleLabeler(API_KEY);
//    EntityRecognizer recognizer = new EntityRecognizer(API_KEY);
//    Parser parser = new Parser(API_KEY);
//    Tagger tagger = new Tagger(API_KEY);

        while (true) {
            System.out.print("분석할 문장을 입력하세요>> ");
            String text;
            try {
                text = br.readLine();
            } catch (Exception e) {
                text = null;
            }

            if (text == null || text.isEmpty())
                break;

            List<Sentence> sentences = labeler.analyze(text);
            for (Sentence sent : sentences) {
                System.out.println("===== Sentence =====");
                System.out.println(sent.singleLineString());

                List<Entity> entities = sent.getEntities();
                if (entities != null) {
                    System.out.println("# Named Entities");
                    for (Entity entity : entities) {
                        System.out.print("[" + entity.getSurface() + "]는 " + entity.getFineLabel() + " 유형의 개체명으로, 형태소 [");
                        for (Morpheme morpheme : entity) {
                            System.out.print(morpheme);
                            System.out.print(' ');
                        }
                        System.out.println("]를 포함합니다.");
                    }
                }

                List<DepEdge> dependencies = sent.getDependencies();
                if (dependencies != null) {
                    System.out.println("# Dependency Parse");
                    for (DepEdge edge : dependencies) {
                        System.out.print("[" + edge.getDependent().getSurface() + "]는 [");
                        Word governor = edge.getGovernor();
                        if (governor == null)
                            System.out.print("ROOT");
                        else
                            System.out.print(governor.getSurface());
                        System.out.print("]의 ");
                        System.out.print(edge.getType());
                        System.out.print('-');
                        System.out.print(edge.getDepType());
                        System.out.println();
                    }
                }

                List<RoleEdge> roles = sent.getRoles();
                if (roles != null) {
                    System.out.println("# Role Labeling");
                    for (RoleEdge edge : roles)
                        System.out.println("[" + edge.getArgument().getSurface() + "]는 [" + edge.getPredicate().getSurface() + "]의 " + edge.getLabel());
                }
            }
        }
    }
}
