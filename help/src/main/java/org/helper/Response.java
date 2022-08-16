package org.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response implements Serializable {

    private static final long serialVersionUID = 13;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : datas){
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString();
    }
}
