package com.contacts;

import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 24.10.14
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class Constants {
    public static final String AUTHORITY = "com.contacts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH_CONTACT = "Contact";
    public static final Uri CONTENT_URI_CONTACT = Uri.parse(CONTENT_URI + "/" + PATH_CONTACT);
}
