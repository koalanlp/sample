const {initialize} = require("koalanlp/Util");
const {RoleLabeler} = require("koalanlp/proc"); // EntityRecognizer, Parser, Tagger
const {ETRI} = require("koalanlp/API");
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let labeler;

async function processText(text){
    if (text == null || text.trim().length === 0) {
        rl.close();
        return;
    }

    let sentences = await labeler(text);
    sentences.forEach((sent, i) => {
        console.log(`===== Sentence #${i} =====`);
        console.log(sent.singleLineString());

        console.log("# Named Entities");
        sent.getEntities().forEach((entity) => {
            console.log(`[${entity.surface}]는 ${entity.fineLabel} 유형의 개체명으로, 형태소 [${entity.joinToString()}]를 포함합니다.`);
        });

        console.log("# Dependency Parse");
        sent.getDependencies().forEach((edge) => {
            console.log(`[${edge.dependent.surface}]는 [${edge.governor ? edge.governor.surface
                : "ROOT"}]의 ${edge.type}-${edge.depType}`);
        });

        console.log("# Role Labeling");
        sent.getRoles().forEach((edge) => {
            console.log(`[${edge.argument.surface}]는 [${edge.predicate.surface}]의 ${edge.label}`);
        });
    });

    readInput();
}

function readInput(){
    rl.question("분석할 문장을 입력하세요>> ", processText);
}

/***********************
 * Main Execution Part *
 ***********************/
const API_KEY = process.argv[2] || process.env['API_KEY'];
if (typeof API_KEY === 'undefined'){
    console.error("ETRI 분석기는 API 키 설정이 필요합니다. API_KEY 환경변수를 설정하거나 프로그램 인자로 key를 전달해주세요.");
    process.exit(1);
}

initialize({packages: {ETRI: 'LATEST'}})
    .then(() => {
        labeler = new RoleLabeler(ETRI, {apiKey: API_KEY});
        readInput();
    }).catch((err) => console.error('Error occurred!', err));