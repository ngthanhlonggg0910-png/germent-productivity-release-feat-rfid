import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  NVButtonTrackingTypeComponentsPage,
  NVButtonTrackingTypeDeleteDialog,
  NVButtonTrackingTypeUpdatePage
} from './nv-button-tracking-type.page-object';

const expect = chai.expect;

describe('NVButtonTrackingType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let nVButtonTrackingTypeComponentsPage: NVButtonTrackingTypeComponentsPage;
  let nVButtonTrackingTypeUpdatePage: NVButtonTrackingTypeUpdatePage;
  let nVButtonTrackingTypeDeleteDialog: NVButtonTrackingTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NVButtonTrackingTypes', async () => {
    await navBarPage.goToEntity('nv-button-tracking-type');
    nVButtonTrackingTypeComponentsPage = new NVButtonTrackingTypeComponentsPage();
    await browser.wait(ec.visibilityOf(nVButtonTrackingTypeComponentsPage.title), 5000);
    expect(await nVButtonTrackingTypeComponentsPage.getTitle()).to.eq('ctApp.nVButtonTrackingType.home.title');
  });

  it('should load create NVButtonTrackingType page', async () => {
    await nVButtonTrackingTypeComponentsPage.clickOnCreateButton();
    nVButtonTrackingTypeUpdatePage = new NVButtonTrackingTypeUpdatePage();
    expect(await nVButtonTrackingTypeUpdatePage.getPageTitle()).to.eq('ctApp.nVButtonTrackingType.home.createOrEditLabel');
    await nVButtonTrackingTypeUpdatePage.cancel();
  });

  it('should create and save NVButtonTrackingTypes', async () => {
    const nbButtonsBeforeCreate = await nVButtonTrackingTypeComponentsPage.countDeleteButtons();

    await nVButtonTrackingTypeComponentsPage.clickOnCreateButton();
    await promise.all([nVButtonTrackingTypeUpdatePage.setNameInput('name'), nVButtonTrackingTypeUpdatePage.setNumberOfStateInput('5')]);
    expect(await nVButtonTrackingTypeUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await nVButtonTrackingTypeUpdatePage.getNumberOfStateInput()).to.eq('5', 'Expected numberOfState value to be equals to 5');
    await nVButtonTrackingTypeUpdatePage.save();
    expect(await nVButtonTrackingTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await nVButtonTrackingTypeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last NVButtonTrackingType', async () => {
    const nbButtonsBeforeDelete = await nVButtonTrackingTypeComponentsPage.countDeleteButtons();
    await nVButtonTrackingTypeComponentsPage.clickOnLastDeleteButton();

    nVButtonTrackingTypeDeleteDialog = new NVButtonTrackingTypeDeleteDialog();
    expect(await nVButtonTrackingTypeDeleteDialog.getDialogTitle()).to.eq('ctApp.nVButtonTrackingType.delete.question');
    await nVButtonTrackingTypeDeleteDialog.clickOnConfirmButton();

    expect(await nVButtonTrackingTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
