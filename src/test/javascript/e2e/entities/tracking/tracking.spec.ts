import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TrackingComponentsPage, TrackingDeleteDialog, TrackingUpdatePage } from './tracking.page-object';

const expect = chai.expect;

describe('Tracking e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let trackingComponentsPage: TrackingComponentsPage;
  let trackingUpdatePage: TrackingUpdatePage;
  let trackingDeleteDialog: TrackingDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Trackings', async () => {
    await navBarPage.goToEntity('tracking');
    trackingComponentsPage = new TrackingComponentsPage();
    await browser.wait(ec.visibilityOf(trackingComponentsPage.title), 5000);
    expect(await trackingComponentsPage.getTitle()).to.eq('ctApp.tracking.home.title');
  });

  it('should load create Tracking page', async () => {
    await trackingComponentsPage.clickOnCreateButton();
    trackingUpdatePage = new TrackingUpdatePage();
    expect(await trackingUpdatePage.getPageTitle()).to.eq('ctApp.tracking.home.createOrEditLabel');
    await trackingUpdatePage.cancel();
  });

  it('should create and save Trackings', async () => {
    const nbButtonsBeforeCreate = await trackingComponentsPage.countDeleteButtons();

    await trackingComponentsPage.clickOnCreateButton();
    await promise.all([
      trackingUpdatePage.setNvButtonIdInput('5'),
      trackingUpdatePage.setState1TimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      trackingUpdatePage.setState2TimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      trackingUpdatePage.setState3TimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      trackingUpdatePage.setState4TimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      trackingUpdatePage.setState5TimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      trackingUpdatePage.setState6TimeInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      trackingUpdatePage.trackingTypeSelectLastOption()
    ]);
    expect(await trackingUpdatePage.getNvButtonIdInput()).to.eq('5', 'Expected nvButtonId value to be equals to 5');
    expect(await trackingUpdatePage.getState1TimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected state1Time value to be equals to 2000-12-31'
    );
    expect(await trackingUpdatePage.getState2TimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected state2Time value to be equals to 2000-12-31'
    );
    expect(await trackingUpdatePage.getState3TimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected state3Time value to be equals to 2000-12-31'
    );
    expect(await trackingUpdatePage.getState4TimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected state4Time value to be equals to 2000-12-31'
    );
    expect(await trackingUpdatePage.getState5TimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected state5Time value to be equals to 2000-12-31'
    );
    expect(await trackingUpdatePage.getState6TimeInput()).to.contain(
      '2001-01-01T02:30',
      'Expected state6Time value to be equals to 2000-12-31'
    );
    await trackingUpdatePage.save();
    expect(await trackingUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await trackingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Tracking', async () => {
    const nbButtonsBeforeDelete = await trackingComponentsPage.countDeleteButtons();
    await trackingComponentsPage.clickOnLastDeleteButton();

    trackingDeleteDialog = new TrackingDeleteDialog();
    expect(await trackingDeleteDialog.getDialogTitle()).to.eq('ctApp.tracking.delete.question');
    await trackingDeleteDialog.clickOnConfirmButton();

    expect(await trackingComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
