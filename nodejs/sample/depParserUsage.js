const {initialize} = require("koalanlp/Util");
const {Parser} = require("koalanlp/proc");
const {KKMA} = require("koalanlp/API");
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let parser;

async function processText(text){
    if (text == null || text.trim().length === 0) {
        rl.close();
        return;
    }

    let sentences = await parser(text);
    sentences.forEach((sent, i) => {
        console.log(`===== Sentence #${i} =====`);
        console.log(sent.singleLineString());

        console.log("# Dependency Parse result");
        sent.getDependencies().forEach((edge) => {
            console.log(`[${edge.dependent.surface}]는 [${edge.governor ? edge.governor.surface : "ROOT"}]의 ${edge.type}-${edge.depType}`);
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
initialize({packages: {KKMA: 'LATEST'}})  //ETRI, HNN
    .then(() => {
        parser = new Parser(KKMA);
        readInput();
    }).catch((err) => console.error('Error occurred!', err));