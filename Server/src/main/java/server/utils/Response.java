package server.utils;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private List<String> datas;

    public Response(List<String> datas) {
        this.datas = datas;
    }
    public Response(){
        datas = new ArrayList<>();
    }

    public List<String> getDatas() {
        return datas;
    }

    public Response Add(String datas){
        this.datas.add(datas);
        return this;
    }
}
