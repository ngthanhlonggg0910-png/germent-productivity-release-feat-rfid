import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MapControlButtonComponentsPage, MapControlButtonDeleteDialog, MapControlButtonUpdatePage } from './map-control-button.page-object';

const expect = chai.expect;

describe('MapControlButton e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mapControlButtonComponentsPage: MapControlButtonComponentsPage;
  let mapControlButtonUpdatePage: MapControlButtonUpdatePage;
  let mapControlButtonDeleteDialog: MapControlButtonDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MapControlButtons', async () => {
    await navBarPage.goToEntity('map-control-button');
    mapControlButtonComponentsPage = new MapControlButtonComponentsPage();
    await browser.wait(ec.visibilityOf(mapControlButtonComponentsPage.title), 5000);
    expect(await mapControlButtonComponentsPage.getTitle()).to.eq('ctApp.mapControlButton.home.title');
  });

  it('should load create MapControlButton page', async () => {
    await mapControlButtonComponentsPage.clickOnCreateButton();
    mapControlButtonUpdatePage = new MapControlButtonUpdatePage();
    expect(await mapControlButtonUpdatePage.getPageTitle()).to.eq('ctApp.mapControlButton.home.createOrEditLabel');
    await mapControlButtonUpdatePage.cancel();
  });

  it('should create and save MapControlButtons', async () => {
    const nbButtonsBeforeCreate = await mapControlButtonComponentsPage.countDeleteButtons();

    await mapControlButtonComponentsPage.clickOnCreateButton();
    await promise.all([
      mapControlButtonUpdatePage.sourceButtonSelectLastOption(),
      mapControlButtonUpdatePage.targetButtonSelectLastOption()
    ]);
    await mapControlButtonUpdatePage.save();
    expect(await mapControlButtonUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mapControlButtonComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MapControlButton', async () => {
    const nbButtonsBeforeDelete = await mapControlButtonComponentsPage.countDeleteButtons();
    await mapControlButtonComponentsPage.clickOnLastDeleteButton();

    mapControlButtonDeleteDialog = new MapControlButtonDeleteDialog();
    expect(await mapControlButtonDeleteDialog.getDialogTitle()).to.eq('ctApp.mapControlButton.delete.question');
    await mapControlButtonDeleteDialog.clickOnConfirmButton();

    expect(await mapControlButtonComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
