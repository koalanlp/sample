package sample;

import kr.bydelta.koala.data.Morpheme;
import kr.bydelta.koala.data.Sentence;
import kr.bydelta.koala.data.Word;
import kr.bydelta.koala.okt.SentenceSplitter;
//import kr.bydelta.koala.hnn.SentenceSplitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class SentenceSplitJava {
    public static void main(String[] args){
        SentenceSplitter splitter = new SentenceSplitter();
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

            List<String> sentences = splitter.sentences(text);
            for(String sent : sentences){
                System.out.println("===== Sentence =====");
                System.out.println(sent);
            }
        }
    }
}
