package sample;

import kotlin.Pair;
import kr.bydelta.koala.POS;
import kr.bydelta.koala.kmr.Dictionary;
//import kr.bydelta.koala.eunjeon.Dictionary;
//import kr.bydelta.koala.hnn.Dictionary;
//import kr.bydelta.koala.kkma.Dictionary;
//import kr.bydelta.koala.okt.Dictionary;

import kr.bydelta.koala.proc.DicUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DictionaryJava {
    public static void main(String[] args){
        Dictionary.INSTANCE.addUserDictionary("하동균", POS.NNP);
        Dictionary.INSTANCE.addUserDictionary(new Pair<String, POS>("나비야", POS.NNP));

        HashSet<POS> set = new HashSet<>();
        set.add(POS.NNP);
        System.out.println(Dictionary.INSTANCE.contains("하동균", set));
        Pair<String, POS>[] notEx = Dictionary.INSTANCE.getNotExists(true, new Pair("하동균", POS.NNP));
        for (Pair<String, POS> word : notEx){
            System.out.println(word.getFirst() + " (Tag=" + word.getSecond() + ")");
        }

        System.out.println("# 접사 목록");
        Iterator<Pair<String, POS>> it = Dictionary.INSTANCE.getBaseEntries(pos -> pos.isAffix());
        while(it.hasNext()){
            Pair<String, POS> word = it.next();
            System.out.println(word.getFirst() + " (Tag=" + word.getSecond() + ")");
        }

        System.out.println("# 사용자 사전 목록");
        Dictionary.INSTANCE.getItems().forEach(word ->
            System.out.println(word.getFirst() + " (Tag=" + word.getSecond() + ")")
        );

        System.out.println("---- 타 분석기 사전 불러오기 ----");
        Dictionary.INSTANCE.importFrom(kr.bydelta.koala.kkma.Dictionary.INSTANCE, false, pos -> pos.isAffix());
        System.out.println("# 사용자 사전 목록 (30개)");
        Iterator<Pair<String, POS>> userIt = Dictionary.INSTANCE.getItems().iterator();
        for(int i=0; i < 30 && userIt.hasNext(); i++){
            Pair<String, POS> word = userIt.next();
            System.out.println(word.getFirst() + " (Tag=" + word.getSecond() + ")");
        }
    }
}
