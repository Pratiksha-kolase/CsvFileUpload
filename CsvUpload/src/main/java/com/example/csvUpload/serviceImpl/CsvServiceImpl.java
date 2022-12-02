package com.example.csvUpload.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.csvUpload.dao.CsvRepo;
import com.example.csvUpload.model.CsvModel;
import com.example.csvUpload.service.CsvService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Service
public class CsvServiceImpl implements CsvService{

	@Autowired
	private CsvRepo csvRepo;
	
	@Override
	public ResponseEntity<String> upload(MultipartFile multipartFile) throws IOException {
		List<CsvModel> list = csvRepo.findAll();
		CsvParserSettings csvParser = new CsvParserSettings();
		csvParser.setHeaderExtractionEnabled(true);
		CsvParser par = new CsvParser(csvParser);
		Iterable<Record> data = par.parseAllRecords(multipartFile.getInputStream());
		for(Record record:data) {
			CsvModel csvModel=new CsvModel();
			csvModel.setItemId(record.getLong("ItemID"));
			csvModel.setItemName(record.getString("Item Name"));
			csvModel.setBrand(record.getString("Brand"));
			csvModel.setDimensionUnit(record.getString("Dimension Unit"));
			csvModel.setEan(record.getString("EAN"));
			csvModel.setInventoryAccount(record.getString("Inventory Account"));
			csvModel.setIsbn(record.getString("ISBN"));
			csvModel.setIsComboProduct(record.getBoolean("Is Combo Product"));
			csvModel.setIsReturnableItem(record.getBoolean("Is Returnable Item"));
            csvModel.setItemType(record.getString("Item Type"));
            csvModel.setLastSyncTime(record.getString("Last Sync Time"));
            csvModel.setManufacturer(record.getString("Manufacturer"));
            csvModel.setOpeningStock(record.getString("Opening Stock"));
            csvModel.setOpeningStockValue(record.getString("Opening Stock Value"));
            csvModel.setPackageHeight(record.getLong("Package Height"));
            csvModel.setPackageLength(record.getLong("Package Length"));
            csvModel.setPackageWeight(record.getLong("Package Weight"));
            csvModel.setPackageWidth(record.getLong("Package Width"));
            csvModel.setPartNumber(record.getLong("Part Number"));
            csvModel.setPreferredVendor(record.getString("Preferred Vendor"));
            csvModel.setProductTypes(record.getString("Product Type"));
            csvModel.setPurchaseAccount(record.getString("Purchase Account"));
            csvModel.setPurchaseDescription(record.getString("Purchase Description"));
            csvModel.setPurchasePrice(record.getString("Purchase Price"));
            csvModel.setRecoderLevel(record.getString("Reorder Level"));
            csvModel.setReferanceId(record.getLong("Reference ID"));
            csvModel.setSalesAccount(record.getString("Sales Account"));
            csvModel.setSalesDescription(record.getString("Sales Description"));
            csvModel.setSellingPrice(record.getString("Selling Price"));
            csvModel.setSku(record.getString("SKU"));
            csvModel.setSource(record.getLong("Source"));
            csvModel.setStatus(record.getString("Status"));
            csvModel.setStockOnHand(record.getLong("Stock On Hand"));
            csvModel.setTaxName(record.getString("Tax Name"));
            csvModel.setTaxPercentage(record.getLong("Tax Percentage"));
            csvModel.setTaxType(record.getString("Tax Type"));
            csvModel.setUnit(record.getString("Unit"));
            csvModel.setUpc(record.getString("UPC"));
            csvModel.setWeightUnit(record.getString("Weight Unit"));
            list.add(csvModel);
            
		}
		csvRepo.saveAll(list);
		return new ResponseEntity<>("uploaded file",HttpStatus.OK);
	}


}
