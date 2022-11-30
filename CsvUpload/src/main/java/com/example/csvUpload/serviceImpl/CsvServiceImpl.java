package com.example.csvUpload.serviceImpl;

import java.io.IOException;
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
		for(Record r:data)
		{
			CsvModel csvModel=new CsvModel();
			csvModel.setItemId(r.getString("Item Id"));
			csvModel.setItemName(r.getString("Item Name"));
			csvModel.setBrand(r.getString("brand"));
			csvModel.setDimensionUnit(r.getString("dimensionUnit"));
			csvModel.setEan(r.getString("ean"));
			csvModel.setInventoryAccount(r.getString("inventoryAccount"));
			csvModel.setIsbn(r.getString("isbn"));
			csvModel.setIsComboProduct(r.getBoolean("isComboProduct"));
			csvModel.setIsReturnableItem(r.getBoolean("isReturnableItem"));
            csvModel.setItemType(r.getString("itemType"));
            csvModel.setLastSyncTime(r.getString("lastSyncTime"));
            csvModel.setManufacturer(r.getString("manufacturer"));
            csvModel.setOpeningStock(r.getString("openingStock"));
            csvModel.setOpeningStockValue(r.getString("openingStockValue"));
            csvModel.setPackageHeight(r.getLong("packageHeight"));
            csvModel.setPackageLength(r.getLong("packageLength"));
            csvModel.setPackageWeight(r.getLong("packageWeight"));
            csvModel.setPackageWidth(r.getLong("packageWidth"));
            csvModel.setPartNumber(r.getLong("partNumber"));
            csvModel.setPreferredVendor(r.getString("preferredVendor"));
            csvModel.setProductTypes(r.getString("productTypes"));
            csvModel.setPurchaseAccount(r.getString("purchaseAccount"));
            csvModel.setPurchaseDescription(r.getString("purchaseDescription"));
            csvModel.setPurchasePrice(r.getString("purchasePrice"));
            csvModel.setRecoderLevel(r.getString("recoderLevel"));
            csvModel.setReferanceId(r.getLong("referanceId"));
            csvModel.setSalesAccount(r.getString("salesAccount"));
            csvModel.setSalesDescription(r.getString("Sales Description"));
            csvModel.setSellingPrice(r.getString("sellingPrice"));
            csvModel.setSku(r.getString("sku"));
            csvModel.setSource(r.getLong("source"));
            csvModel.setStatus(r.getString("status"));
            csvModel.setStockOnHand(r.getLong("stockOnHand"));
            csvModel.setTaxName(r.getString("taxName"));
            csvModel.setTaxPercentage(r.getLong("taxPercentage"));
            csvModel.setTaxType(r.getString("taxType"));
            csvModel.setUnit(r.getString("unit"));
            csvModel.setUpc(r.getString("upc"));
            csvModel.setWeightUnit(r.getString("weightUnit"));
            list.add(csvModel);
            
            csvRepo.saveAll(list);
            System.out.println(list);
		}
		return new ResponseEntity<>("uploaded file",HttpStatus.OK);
	}


}
