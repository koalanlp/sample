package sample;

import kr.bydelta.koala.data.DepEdge;
import kr.bydelta.koala.data.Sentence;
import kr.bydelta.koala.data.Word;
import kr.bydelta.koala.kkma.Parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
// import kr.bydelta.koala.etri.Parser;
// import kr.bydelta.koala.hnn.Tagger;

public class DepParserJava {
    public static void main(String[] args){
        Parser parser = new Parser();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.print("분석할 문장을 입력하세요>> ");
            String text;
            try{
                text = br.readLine();
            }catch (Exception e){
                text = null;
            }

            if (text == null || text.isEmpty())
                break;

            List<Sentence> sentences = parser.analyze(text);
            for(Sentence sent : sentences){
                System.out.println("===== Sentence =====");
                System.out.println(sent.singleLineString());

                System.out.println("# Dependency Parse result");
                List<DepEdge> dependencies = sent.getDependencies();
                if(dependencies != null)
                    for(DepEdge edge : dependencies){
                        System.out.print("[" + edge.getDependent().getSurface() + "]는 [");
                        Word governor = edge.getGovernor();
                        if(governor == null)
                            System.out.print("ROOT");
                        else
                            System.out.print(governor.getSurface());
                        System.out.print("]의 ");
                        System.out.print(edge.getType());
                        System.out.print('-');
                        System.out.print(edge.getDepType());
                        System.out.println();
                    }
                else
                    System.out.println("(Unexpected) NULL!");
            }
        }
    }
}
