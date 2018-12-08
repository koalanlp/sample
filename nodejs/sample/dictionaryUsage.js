const {initialize} = require("koalanlp/Util");
const {Dictionary} = require("koalanlp/proc"); // EntityRecognizer, Parser, Tagger
const {POS} = require("koalanlp/types");
const {KMR, KKMA} = require("koalanlp/API");

initialize({packages: {KMR: 'LATEST', KKMA: 'LATEST'}})
    .then(async () => {
        let kmrDict = new Dictionary(KMR);
        kmrDict.addUserDictionary({surface: "하동균", tag: POS.NNP});
        kmrDict.addUserDictionary({surface: "나비야", tag: POS.NNP});

        console.log(kmrDict.contains("하동균"));
        console.log(kmrDict.contains("하동균", POS.NNP));
        console.log(await kmrDict.getNotExists(true, {surface: "하동균", tag: POS.NNP}));

        // As a promise
        kmrDict.getBaseEntries((it) => it.isAffix()).then((iterator) => {
            console.log("# 접사 목록");
            while (true) {
                let next = iterator.next();
                if (next.done) break;

                let it = next.value;
                console.log(`[접사목록] ${it.surface} (Tag=${it.tag.toString()})`);
            }
        }).catch((err) => console.error('Error occurred while executing getBaseEntries', err));

        kmrDict.getItems().then((items) => {
            console.log("# 사용자 사전 목록");
            items.forEach((it) => {
                console.log(`[사용자] ${it.surface} (Tag=${it.tag.toString()})`);
            })
        }).catch((err) => console.error('Error occurred while executing getItems', err));

        // Using async/await
        console.log("---- 타 분석기 사전 불러오기 ----");
        await kmrDict.importFrom(new Dictionary(KKMA));
        console.log("# 사용자 사전 목록 (30개)");
        (await kmrDict.getItems()).slice(0, 30).forEach((it) => {
            console.log(`${it.surface} (Tag=${it.tag.toString()})`);
        });
    }).catch((err) => console.error('Error occurred!', err));