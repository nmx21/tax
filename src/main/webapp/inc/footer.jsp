<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/jstl/custom_tag_attribute.tld" prefix="CustomAttribute" %>
<div style="position:absolute; bottom:0;width:100%;height:30px;background:#66ccff;text-align: center;">
    <CustomAttribute:Time message="Поточний час" />
</div>