package vip.designpattern.decorator;

import vip.designpattern.decorator.roles.IPermission;
import vip.designpattern.decorator.roles.impl.Admin;
import vip.designpattern.decorator.roles.impl.Normal;
import vip.designpattern.decorator.roles.impl.Tourists;
import vip.designpattern.decorator.roles.impl.Vip;

public class Test {

    public static void main(String[] args) {
        /** 管理员权限 */
        IPermission admin = new Admin();
        System.out.println("管理员导航条:");
        System.out.println(admin.showPermNavBar());
        System.out.println("^^^^^^^^^^^^^^^^^^");

        /** vip会员权限 */
        IPermission vip = new Vip();
        System.out.println("vip会员导航条:");
        System.out.println(vip.showPermNavBar());
        System.out.println("^^^^^^^^^^^^^^^^^^");

        /** 普通会员权限 */
        IPermission normal = new Normal();
        System.out.println("普通会员导航条:");
        System.out.println(normal.showPermNavBar());
        System.out.println("^^^^^^^^^^^^^^^^^^");

        /** 游客权限 */
        IPermission tourists = new Tourists();
        System.out.println("游客导航条:");
        System.out.println(tourists.showPermNavBar());
        System.out.println("^^^^^^^^^^^^^^^^^^");
    }
}
