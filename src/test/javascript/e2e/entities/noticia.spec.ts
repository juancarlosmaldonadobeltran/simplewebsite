import { browser, element, by, $ } from 'protractor';
import { NavBarPage } from './../page-objects/jhi-page-objects';
const path = require('path');

describe('Noticia e2e test', () => {

    let navBarPage: NavBarPage;
    let noticiaDialogPage: NoticiaDialogPage;
    let noticiaComponentsPage: NoticiaComponentsPage;
    const fileToUpload = '../../../../main/webapp/content/images/logo-jhipster.png';
    const absolutePath = path.resolve(__dirname, fileToUpload);
    

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Noticias', () => {
        navBarPage.goToEntity('noticia');
        noticiaComponentsPage = new NoticiaComponentsPage();
        expect(noticiaComponentsPage.getTitle()).toMatch(/Noticias/);

    });

    it('should load create Noticia dialog', () => {
        noticiaComponentsPage.clickOnCreateButton();
        noticiaDialogPage = new NoticiaDialogPage();
        expect(noticiaDialogPage.getModalTitle()).toMatch(/Create or edit a Noticia/);
        noticiaDialogPage.close();
    });

    it('should create and save Noticias', () => {
        noticiaComponentsPage.clickOnCreateButton();
        noticiaDialogPage.setFechaInput('2000-12-31');
        expect(noticiaDialogPage.getFechaInput()).toMatch('2000-12-31');
        noticiaDialogPage.setTituloInput('titulo');
        expect(noticiaDialogPage.getTituloInput()).toMatch('titulo');
        noticiaDialogPage.setContenidoInput('contenido');
        expect(noticiaDialogPage.getContenidoInput()).toMatch('contenido');
        noticiaDialogPage.save();
        expect(noticiaDialogPage.getSaveButton().isPresent()).toBeFalsy();
    }); 

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});

export class NoticiaComponentsPage {
    createButton = element(by.css('.jh-create-entity'));
    title = element.all(by.css('jhi-noticia div h2 span')).first();

    clickOnCreateButton() {
        return this.createButton.click();
    }

    getTitle() {
        return this.title.getText();
    }
}

export class NoticiaDialogPage {
    modalTitle = element(by.css('h4#myNoticiaLabel'));
    saveButton = element(by.css('.modal-footer .btn.btn-primary'));
    closeButton = element(by.css('button.close'));
    fechaInput = element(by.css('input#field_fecha'));
    tituloInput = element(by.css('input#field_titulo'));
    contenidoInput = element(by.css('input#field_contenido'));

    getModalTitle() {
        return this.modalTitle.getText();
    }

    setFechaInput = function (fecha) {
        this.fechaInput.sendKeys(fecha);
    }

    getFechaInput = function () {
        return this.fechaInput.getAttribute('value');
    }

    setTituloInput = function (titulo) {
        this.tituloInput.sendKeys(titulo);
    }

    getTituloInput = function () {
        return this.tituloInput.getAttribute('value');
    }

    setContenidoInput = function (contenido) {
        this.contenidoInput.sendKeys(contenido);
    }

    getContenidoInput = function () {
        return this.contenidoInput.getAttribute('value');
    }

    save() {
        this.saveButton.click();
    }

    close() {
        this.closeButton.click();
    }

    getSaveButton() {
        return this.saveButton;
    }
}
