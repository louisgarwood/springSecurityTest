package org.cisecurity

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RequestmapController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Requestmap.list(params), model:[requestmapCount: Requestmap.count()]
    }

    def show(Requestmap requestmap) {
        respond requestmap
    }

    def create() {
        respond new Requestmap(params)
    }

    @Transactional
    def save(Requestmap requestmap) {
        if (requestmap == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requestmap.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requestmap.errors, view:'create'
            return
        }

        requestmap.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'requestmap.label', default: 'Requestmap'), requestmap.id])
                redirect requestmap
            }
            '*' { respond requestmap, [status: CREATED] }
        }
    }

    def edit(Requestmap requestmap) {
        respond requestmap
    }

    @Transactional
    def update(Requestmap requestmap) {
        if (requestmap == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (requestmap.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond requestmap.errors, view:'edit'
            return
        }

        requestmap.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'requestmap.label', default: 'Requestmap'), requestmap.id])
                redirect requestmap
            }
            '*'{ respond requestmap, [status: OK] }
        }
    }

    @Transactional
    def delete(Requestmap requestmap) {

        if (requestmap == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        requestmap.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'requestmap.label', default: 'Requestmap'), requestmap.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'requestmap.label', default: 'Requestmap'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
