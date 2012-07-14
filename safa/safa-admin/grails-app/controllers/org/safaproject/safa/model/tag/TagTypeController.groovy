package org.safaproject.safa.model.tag

class TagTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tagTypeInstanceList: TagType.list(params), tagTypeInstanceTotal: TagType.count()]
    }

    def create = {
        def tagTypeInstance = new TagType()
        tagTypeInstance.properties = params
        return [tagTypeInstance: tagTypeInstance]
    }

    def save = {
        def tagTypeInstance = new TagType(params)
        if (tagTypeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tagType.label', default: 'TagType'), tagTypeInstance.id])}"
            redirect(action: "show", id: tagTypeInstance.id)
        }
        else {
            render(view: "create", model: [tagTypeInstance: tagTypeInstance])
        }
    }

    def show = {
        def tagTypeInstance = TagType.get(params.id)
        if (!tagTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagType.label', default: 'TagType'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tagTypeInstance: tagTypeInstance]
        }
    }

    def edit = {
        def tagTypeInstance = TagType.get(params.id)
        if (!tagTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagType.label', default: 'TagType'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tagTypeInstance: tagTypeInstance]
        }
    }

    def update = {
        def tagTypeInstance = TagType.get(params.id)
        if (tagTypeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tagTypeInstance.version > version) {
                    
                    tagTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tagType.label', default: 'TagType')] as Object[], "Another user has updated this TagType while you were editing")
                    render(view: "edit", model: [tagTypeInstance: tagTypeInstance])
                    return
                }
            }
            tagTypeInstance.properties = params
            if (!tagTypeInstance.hasErrors() && tagTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tagType.label', default: 'TagType'), tagTypeInstance.id])}"
                redirect(action: "show", id: tagTypeInstance.id)
            }
            else {
                render(view: "edit", model: [tagTypeInstance: tagTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagType.label', default: 'TagType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tagTypeInstance = TagType.get(params.id)
        if (tagTypeInstance) {
            try {
                tagTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tagType.label', default: 'TagType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tagType.label', default: 'TagType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tagType.label', default: 'TagType'), params.id])}"
            redirect(action: "list")
        }
    }
}
