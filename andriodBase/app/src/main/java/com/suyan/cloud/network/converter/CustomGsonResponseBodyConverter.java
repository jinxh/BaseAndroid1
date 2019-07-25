package com.suyan.cloud.network.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class CustomGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        String response = value.string();
//
//        if(TextUtils.isEmpty(response)){
//            throw new ApiException("-1", "返回数据为空");
//        }
//        HttpStatus httpStatus = gson.fromJson(response, HttpStatus.class);
//        //// FIXME: 17/1/19 等平台统一返回成功码000后添加
////        if (httpStatus.isCodeInvalid()) {
////            value.close();
////            throw new ApiException(httpStatus.getCode(), httpStatus.getMessage());
////        }
//        MediaType contentType = value.contentType();
//        Charset charset = contentType != null ? contentType.charset(Charset.forName("UTF-8")) : Charset.forName("UTF-8");
//        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
//        Reader reader = new InputStreamReader(inputStream, charset);
//        JsonReader jsonReader = gson.newJsonReader(reader);

        JsonReader jsonReader = new JsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}

