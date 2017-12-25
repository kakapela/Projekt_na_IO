package sample.Model;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
//"https://docs.oracle.com/javase/8/docs/"
public class WebViewModel {
    private WebEngine engine;
    private  WebView webView;
    private String url_adress;
    public void initalize(WebEngine engine,WebView webView,String url_adress){
        this.engine = engine;
        this.webView = webView;
        this.url_adress = url_adress;
        this.engine=webView.getEngine();
        this.engine.load(url_adress);
    }
    public void reload(){
        engine.load(url_adress);
    }
}
