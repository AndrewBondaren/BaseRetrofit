package api.retrofit;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

@RequiredArgsConstructor(staticName = "create")
public class SSLSocketFactory  {

    //TODO make private?
    @SneakyThrows
    public static javax.net.ssl.SSLSocketFactory getTrustAll() {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new AllTrustManager() }, null);
        return sc.getSocketFactory();
    }

}
