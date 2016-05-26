package base



import org.junit.*
import grails.test.mixin.*

@TestFor(UpldFileController)
@Mock(UpldFile)
class UpldFileControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/upldFile/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.upldFileInstanceList.size() == 0
        assert model.upldFileInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.upldFileInstance != null
    }

    void testSave() {
        controller.save()

        assert model.upldFileInstance != null
        assert view == '/upldFile/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/upldFile/show/1'
        assert controller.flash.message != null
        assert UpldFile.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/upldFile/list'

        populateValidParams(params)
        def upldFile = new UpldFile(params)

        assert upldFile.save() != null

        params.id = upldFile.id

        def model = controller.show()

        assert model.upldFileInstance == upldFile
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/upldFile/list'

        populateValidParams(params)
        def upldFile = new UpldFile(params)

        assert upldFile.save() != null

        params.id = upldFile.id

        def model = controller.edit()

        assert model.upldFileInstance == upldFile
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/upldFile/list'

        response.reset()

        populateValidParams(params)
        def upldFile = new UpldFile(params)

        assert upldFile.save() != null

        // test invalid parameters in update
        params.id = upldFile.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/upldFile/edit"
        assert model.upldFileInstance != null

        upldFile.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/upldFile/show/$upldFile.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        upldFile.clearErrors()

        populateValidParams(params)
        params.id = upldFile.id
        params.version = -1
        controller.update()

        assert view == "/upldFile/edit"
        assert model.upldFileInstance != null
        assert model.upldFileInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/upldFile/list'

        response.reset()

        populateValidParams(params)
        def upldFile = new UpldFile(params)

        assert upldFile.save() != null
        assert UpldFile.count() == 1

        params.id = upldFile.id

        controller.delete()

        assert UpldFile.count() == 0
        assert UpldFile.get(upldFile.id) == null
        assert response.redirectedUrl == '/upldFile/list'
    }
}
