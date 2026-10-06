import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MapButtonActionComponentsPage, MapButtonActionDeleteDialog, MapButtonActionUpdatePage } from './map-button-action.page-object';

const expect = chai.expect;

describe('MapButtonAction e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let mapButtonActionComponentsPage: MapButtonActionComponentsPage;
  let mapButtonActionUpdatePage: MapButtonActionUpdatePage;
  let mapButtonActionDeleteDialog: MapButtonActionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load MapButtonActions', async () => {
    await navBarPage.goToEntity('map-button-action');
    mapButtonActionComponentsPage = new MapButtonActionComponentsPage();
    await browser.wait(ec.visibilityOf(mapButtonActionComponentsPage.title), 5000);
    expect(await mapButtonActionComponentsPage.getTitle()).to.eq('ctApp.mapButtonAction.home.title');
  });

  it('should load create MapButtonAction page', async () => {
    await mapButtonActionComponentsPage.clickOnCreateButton();
    mapButtonActionUpdatePage = new MapButtonActionUpdatePage();
    expect(await mapButtonActionUpdatePage.getPageTitle()).to.eq('ctApp.mapButtonAction.home.createOrEditLabel');
    await mapButtonActionUpdatePage.cancel();
  });

  it('should create and save MapButtonActions', async () => {
    const nbButtonsBeforeCreate = await mapButtonActionComponentsPage.countDeleteButtons();

    await mapButtonActionComponentsPage.clickOnCreateButton();
    await promise.all([
      mapButtonActionUpdatePage.setSourceAliasStateInput('5'),
      mapButtonActionUpdatePage.targetStateSelectLastOption(),
      mapButtonActionUpdatePage.trackingTypeSelectLastOption()
    ]);
    expect(await mapButtonActionUpdatePage.getSourceAliasStateInput()).to.eq('5', 'Expected sourceAliasState value to be equals to 5');
    await mapButtonActionUpdatePage.save();
    expect(await mapButtonActionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await mapButtonActionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last MapButtonAction', async () => {
    const nbButtonsBeforeDelete = await mapButtonActionComponentsPage.countDeleteButtons();
    await mapButtonActionComponentsPage.clickOnLastDeleteButton();

    mapButtonActionDeleteDialog = new MapButtonActionDeleteDialog();
    expect(await mapButtonActionDeleteDialog.getDialogTitle()).to.eq('ctApp.mapButtonAction.delete.question');
    await mapButtonActionDeleteDialog.clickOnConfirmButton();

    expect(await mapButtonActionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
