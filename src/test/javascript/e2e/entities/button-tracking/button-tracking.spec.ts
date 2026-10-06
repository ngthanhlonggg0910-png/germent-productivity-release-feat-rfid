import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ButtonTrackingComponentsPage, ButtonTrackingDeleteDialog, ButtonTrackingUpdatePage } from './button-tracking.page-object';

const expect = chai.expect;

describe('ButtonTracking e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let buttonTrackingComponentsPage: ButtonTrackingComponentsPage;
  let buttonTrackingUpdatePage: ButtonTrackingUpdatePage;
  let buttonTrackingDeleteDialog: ButtonTrackingDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ButtonTrackings', async () => {
    await navBarPage.goToEntity('button-tracking');
    buttonTrackingComponentsPage = new ButtonTrackingComponentsPage();
    await browser.wait(ec.visibilityOf(buttonTrackingComponentsPage.title), 5000);
    expect(await buttonTrackingComponentsPage.getTitle()).to.eq('ctApp.buttonTracking.home.title');
  });

  it('should load create ButtonTracking page', async () => {
    await buttonTrackingComponentsPage.clickOnCreateButton();
    buttonTrackingUpdatePage = new ButtonTrackingUpdatePage();
    expect(await buttonTrackingUpdatePage.getPageTitle()).to.eq('ctApp.buttonTracking.home.createOrEditLabel');
    await buttonTrackingUpdatePage.cancel();
  });

  it('should create and save ButtonTrackings', async () => {
    const nbButtonsBeforeCreate = await buttonTrackingComponentsPage.countDeleteButtons();

    await buttonTrackingComponentsPage.clickOnCreateButton();
    await promise.all([
      buttonTrackingUpdatePage.setUpdateDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      buttonTrackingUpdatePage.setCurrentAliasStateInput('5'),
      buttonTrackingUpdatePage.setTrackingIdInput('5')
    ]);
    expect(await buttonTrackingUpdatePage.getUpdateDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected updateDate value to be equals to 2000-12-31'
    );
    expect(await buttonTrackingUpdatePage.getCurrentAliasStateInput()).to.eq('5', 'Expected currentAliasState value to be equals to 5');
    expect(await buttonTrackingUpdatePage.getTrackingIdInput()).to.eq('5', 'Expected trackingId value to be equals to 5');
    await buttonTrackingUpdatePage.save();
    expect(await buttonTrackingUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await buttonTrackingComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ButtonTracking', async () => {
    const nbButtonsBeforeDelete = await buttonTrackingComponentsPage.countDeleteButtons();
    await buttonTrackingComponentsPage.clickOnLastDeleteButton();

    buttonTrackingDeleteDialog = new ButtonTrackingDeleteDialog();
    expect(await buttonTrackingDeleteDialog.getDialogTitle()).to.eq('ctApp.buttonTracking.delete.question');
    await buttonTrackingDeleteDialog.clickOnConfirmButton();

    expect(await buttonTrackingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
