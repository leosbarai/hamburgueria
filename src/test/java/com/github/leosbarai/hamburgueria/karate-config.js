function fn(){
    var env = karate.env;

    if(!env){
        env = 'local';
    }

    var config = {
        environment: 'http://localhost:8080'
    };

    return config;
}