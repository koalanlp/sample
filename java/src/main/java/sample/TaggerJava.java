package sample;

import kr.bydelta.koala.data.Morpheme;
import kr.bydelta.koala.data.Sentence;
import kr.bydelta.koala.data.Word;
import kr.bydelta.koala.eunjeon.Tagger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
// import kr.bydelta.koala.kmr.Tagger;
// import kr.bydelta.koala.etri.Tagger;
// import kr.bydelta.koala.eunjeon.Tagger;
// import kr.bydelta.koala.arirang.Tagger;
// import kr.bydelta.koala.daon.Tagger;
// import kr.bydelta.koala.rhino.Tagger;
// import kr.bydelta.koala.okt.Tagger;
// import kr.bydelta.koala.kkma.Tagger;
// import kr.bydelta.koala.hnn.Tagger;

public class TaggerJava {
    public static void main(String[] args){
        Tagger tagger = new Tagger();
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

            List<Sentence> sentences = tagger.tag(text);
            for(Sentence sent : sentences){
                System.out.println("===== Sentence =====");
                System.out.println(sent.surfaceString());

                System.out.println("# Analysis Result");
                // println(sent.singleLineString())

                for(Word word : sent){
                    System.out.print("Word [" + word.getId() + "] " + word.getSurface() + " = ");

                    for(Morpheme morph : word){
                        System.out.print(morph.getSurface() + "/" + morph.getTag() + " ");
                    }
                    System.out.println();
                }
            }
        }
    }
}
