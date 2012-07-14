
<%@ page import="org.safaproject.safa.model.tag.TagType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tagType.label', default: 'TagType')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'tagType.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="tagDataType" title="${message(code: 'tagType.tagDataType.label', default: 'Tag Data Type')}" />
                        
                            <g:sortableColumn property="tagName" title="${message(code: 'tagType.tagName.label', default: 'Tag Name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tagTypeInstanceList}" status="i" var="tagTypeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${tagTypeInstance.id}">${fieldValue(bean: tagTypeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: tagTypeInstance, field: "tagDataType")}</td>
                        
                            <td>${fieldValue(bean: tagTypeInstance, field: "tagName")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${tagTypeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
