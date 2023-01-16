package com.tax.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class CustomAttribute extends SimpleTagSupport {

    StringWriter sw = new StringWriter();
    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }

    @Override
    public void doTag()
            throws JspException, IOException {
        if (message != null) {
            /* Use message from attribute */
            JspWriter out = getJspContext().getOut();
            out.println("<div id=\"current_date_time_block2\"></div>\n" +
                    "<script type=\"text/javascript\">\n" +
                    "\n" +
                    "function zero_first_format(value)\n" +
                    "    {\n" +
                    "        if (value < 10)\n" +
                    "        {\n" +
                    "            value='0'+value;\n" +
                    "        }\n" +
                    "        return value;\n" +
                    "    }\n" +
                    "\n" +
                    "    /* функция получения текущей даты и времени */\n" +
                    "    function date_time()\n" +
                    "    {\n" +
                    "        var current_datetime = new Date();\n" +
                    "        var day = zero_first_format(current_datetime.getDate());\n" +
                    "        var month = zero_first_format(current_datetime.getMonth()+1);\n" +
                    "        var year = current_datetime.getFullYear();\n" +
                    "        var hours = zero_first_format(current_datetime.getHours());\n" +
                    "        var minutes = zero_first_format(current_datetime.getMinutes());\n" +
                    "        var seconds = zero_first_format(current_datetime.getSeconds());\n" +
                    "\n" +
                    "        return \" " + message + " [ \"+day+\".\"+month+\".\"+year+\" \"+hours+\":\"+minutes+\":\"+seconds+\" ]\";\n" +
                    "    };\n" +
                    "    setInterval(function () {\n" +
                    "        document.getElementById('current_date_time_block2').innerHTML = date_time();\n" +
                    "    }, 1000);\n" +
                    "</script>\n"
            );
        } else {
            /* use message from the body */
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }

}