

<%@ page import="org.safaproject.safa.model.content.Resource" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'resource.label', default: 'Resource')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${resourceInstance}">
            <div class="errors">
                <g:renderErrors bean="${resourceInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${resourceInstance?.id}" />
                <g:hiddenField name="version" value="${resourceInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="description"><g:message code="resource.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${resourceInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="resourceType"><g:message code="resource.resourceType.label" default="Resource Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'resourceType', 'errors')}">
                                    <g:select name="resourceType.id" from="${org.safaproject.safa.model.tag.Tag.list()}" optionKey="id" value="${resourceInstance?.resourceType?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="size"><g:message code="resource.size.label" default="Size" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'size', 'errors')}">
                                    <g:textField name="size" value="${fieldValue(bean: resourceInstance, field: 'size')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="url"><g:message code="resource.url.label" default="Url" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: resourceInstance, field: 'url', 'errors')}">
                                    <g:textField name="url" value="${resourceInstance?.url}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
