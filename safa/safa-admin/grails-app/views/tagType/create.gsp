

<%@ page import="org.safaproject.safa.model.tag.TagType" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tagType.label', default: 'TagType')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tagTypeInstance}">
            <div class="errors">
                <g:renderErrors bean="${tagTypeInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagDataType"><g:message code="tagType.tagDataType.label" default="Tag Data Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tagTypeInstance, field: 'tagDataType', 'errors')}">
                                    <g:select name="tagDataType" from="${org.safaproject.safa.model.tag.TagDataTypes?.values()}" keys="${org.safaproject.safa.model.tag.TagDataTypes?.values()*.name()}" value="${tagTypeInstance?.tagDataType?.name()}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tagName"><g:message code="tagType.tagName.label" default="Tag Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: tagTypeInstance, field: 'tagName', 'errors')}">
                                    <g:textField name="tagName" value="${tagTypeInstance?.tagName}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
