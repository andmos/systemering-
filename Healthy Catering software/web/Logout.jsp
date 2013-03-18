<!--This script simply redirect you after it has destroyed the the session witch is currently running -->

<%@ page session="true"%>
    <% session.invalidate();
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "index.xhtml");
%>

