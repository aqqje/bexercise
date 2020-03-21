package vip.designpattern.factorymode;

/**
 * 国内支付
 */
public class DomesticPayment extends AbstractPayFactory {

    public IPay createAliPay(){ super.init();return new AliPay();
    }

    public IPay createUniPay(){ super.init();return new UniPay();
    }

    public IPay createWxPay(){ super.init();return new AliPay();}
}
