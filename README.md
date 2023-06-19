# projectDemo

select * from slam_ausr;
alter table slam_ausr alter column ausr_pwd  type varchar(500);
update slam_ausr set ausr_pwd='9fe0f07b81e830ecaa9af5fb7963814809c98d393dd67c4599b0d2526e746a6f' where ausr_id=1;
SELECT * FROM slam_ausr WHERE ausr_email ='akshayavarma39@gmail.com' AND ausr_pwd ='9fe0f07b81e830ecaa9af5fb7963814809c98d393dd67c4599b0d2526e746a6f';




CREATE TABLE slam_ProductCategories ( prct_id SERIAL PRIMARY KEY, prct_title VARCHAR, prct_desc TEXT );
select * from slam_ProductCategories
insert into slam_ProductCategories(prct_title,prct_desc) values('Computers and Laptops','Computers are Electronic devices that process data and perform tasks based on instructions wherease Laptops are Portable computers that offer the convenience of mobility and functionality of a desktop computer in a compact form')
insert into slam_ProductCategories(prct_title,prct_desc) values('Mobile Phones and Tablets','Mobile Phones are Portable devices for communication and digital interaction. 
wherease Tablets are Compact touchscreen devices for multimedia consumption, productivity, and browsing')
insert into slam_ProductCategories(prct_title,prct_desc) values('Audio and Video','Headphones and Earphones: Audio devices worn over the ears or inserted into the ear canal for personal listening experience.
    Speakers: Devices that produce sound and are used for audio playback in various settings.
    Home Theater Systems: A combination of audio and video components that create a cinematic experience in a home setting.
    Televisions: Visual display devices that receive and display broadcasted or streaming video content.
    Projectors: Devices that project video and images onto a screen or surface for larger display sizes.
    MP3 Players: Portable digital audio devices used for playing MP3 and other audio files on the go.')
    
insert into slam_ProductCategories(prct_title,prct_desc) values('Cameras and Photography','Cameras and photography capture moments and unleash creativity through the lens')

insert into slam_ProductCategories(prct_title,prct_desc) values('Gaming Consoles and Accessories','    Gaming Consoles: Dedicated gaming devices that allow players to experience video games on a television or monitor.
    Gaming Accessories: Additional peripherals or add-ons that enhance the gaming experience, such as controllers, headsets, and gaming keyboards.')
    
insert into slam_ProductCategories(prct_title,prct_desc) values('Office Electronics','Office Electronics: Equipment and devices used in a workplace setting to support productivity and communication, such as computers, printers, scanners, and telephones.')

insert into slam_ProductCategories(prct_title,prct_desc) values('Smart Home Devices','Smart Home Devices are internet-connected devices that enable automation and control of various aspects of a home, such as lighting, security, temperature, and entertainment, for enhanced convenience and efficiency.')

insert into slam_ProductCategories(prct_title,prct_desc) values('Accessories and Components','Accessories: Additional items or products that enhance or complement the main product.
Components: Individual parts or elements that make up a larger product or system.')

insert into slam_ProductCategories(prct_title,prct_desc) values('Home Appliances','Home Appliances: Electrical or mechanical devices designed for use in households to perform various tasks such as cooking, cleaning, cooling, heating, and entertainmen')


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE Table slam_HSN_Code(HSN_CODE INT PRIMARY KEY, sgst numeric(5,2), igst numeric(5,2), cgst numeric(5,2),GST NUMERIC(5,2));
INSERT INTO slam_HSN_Code (HSN_CODE, sgst, igst, cgst, GST)
VALUES
  (1, 5.0, 0.0, 5.0, 10.0),
  (2, 7.5, 7.5, 0.0, 15.0),
  (3, 0.0, 10.0, 10.0, 20.0),
   (4, 8.0, 0.0, 8.0, 16.0),
  (5, 5.5, 5.5, 5.5, 16.5),
  (6, 0.0, 12.0, 12.0, 24.0),
  (7, 6.0, 0.0, 6.0, 12.0),
  (8, 3.5, 3.5, 3.5, 10.5),
  (9, 5.0, 5.0, 5.0, 15.0);

------------------------------------------------------------------------------------------------------------------------------------


CREATE TABLE slam_Products ( Prod_id SERIAL PRIMARY KEY, prod_title VARCHAR(50), prod_prct_id INT references slam_ProductCategories(prct_id), prod_gstc_id int REFERENCES slam_HSN_Code (HSN_CODE), prod_brand VARCHAR(50), image_url varchar(200), prod_desc TEXT, reorderLevel int );

select * from slam_Products
select * from productsdATA
select * from slam_ProductCategories 
select * from slam_HSN_Code

insert into slam_Products(prod_id,prod_title,prod_prct_id,prod_gstc_id,prod_brand,image_url,prod_desc,reorderlevel) values(1,'TWS EarPods',3,6,'TWS','https://shorturl.at/jvIOW','Tws Earbuds Sport in-Ear Headphone Bluetooth Wireless Earbuds Tws Earphone Headset Waterproof Sport Earphone Custom Earphone ',10)
insert into slam_Products values(2,'i12 EarPods',3,6,'TWS','https://shorturl.at/gADY1','i12-Earpods Black Bluetooth Headset Bluetooth',10)
insert into slam_Products values(3,'Boat Airdopes 161',3,6,'Boat','https://shorturl.at/nJMV2','Wireless Earbuds with Massive Playback of upto 17 Hour, IPX5 Water & Sweat Resistance, IWP Technology, Type C Interface',6)
insert into slam_Products values(4,'Buds Pro 2R',3,6,'OnePlus','https://shorturl.at/aefoL','The Buds Pro 2R is a feature-rich Bluetooth headphone model with 11mm woofer and 6mm tweeter dual drivers, 3 microphones per side, and up to 45dB noise cancellation. It offers a transparency mode, 54ms low latency, HeyMelody app support, and dual connection capability. With IP55 water resistance for the earbuds and IPX4 rating for the charging case, it provides protection against sweat and light splashes. The intuitive controls allow for easy playback and call management.',8)
insert into slam_Products values(5,'motorola edge 40',2,3,'Motorola','https://rb.gy/4ahdt','Moto edge 40 with MediaTek 8020 processor with 8GB of LPDDR4X RAM and 256GB of UFS3.1 storage for a powerful experience.',5)
insert into slam_Products values(6,'SAMSUNG GALAXY S23',2,3,'Samsung','https://rb.gy/f8653','samsung s23 with Chipse Qualcomm SM8550-AC Snapdragon 8 Gen 2 (4 nm) and Adreno 740 GPU',12)
insert into slam_Products values(7,'Philips Power Bank',3,6,'Philips','https://shorturl.at/wEK12','11,000mAh big power capacity power bank with LED torch is ideal for traveling or camping. 3 USB ports allows you to charge 3 devices at the same time. ',4);
insert into slam_Products values(8,'MacBook pro m1',1,5,'Apple','https://shorturl.at/josQY','MacBook Pro powered by the all-new M1 Pro and M1 Max',9);
insert into slam_Products values(9,'Lenovo IdeaPad Slim 3i',1,5,'Lenovo','https://shorturl.at/fmAF4','The laptop features a 15.6-inch display, Intel Celeron N4020 processor, integrated graphics, full HD LED-backlit anti-glare display, and runs on Windows 10 Home Basic. It offers a spacious viewing area, smooth performance, decent visuals, vibrant colors, and a user-friendly interface for productivity and entertainment',11);
insert into slam_Products values(10,'Apple ipad mini 6',2,3,'Apple','https://rb.gy/wp7ps','The Apple iPad Mini 6 is a compact powerhouse, offering a stunning 8.3-inch Liquid Retina display, powerful A15 Bionic chip, and versatile features packed into a sleek and portable design. Its the perfect companion for on-the-go productivity, creativity, and entertainment.',3);
insert into slam_Products values(11,'HP M27F1',1,5,'HP','https://shorturl.at/boDKM','HP M27F ips full hd screen monitor 27INCH 2H0N1AA FHD display with IPS technology, 99% sRGB color gamut and Freesync',7)
insert into slam_Products values(12,'JBL Flip5',3,6,'JBL','https://shorturl.at/hoHX3','JBL Flip 5 20W Portable Bluetooth Speaker (IPX7 Water Resistant, JBLs Signature Sound, Stereo Channel, Black)',5);
insert into slam_Products values(13,'SIGNET Multimedia speaker 4.1',3,6,'Signet','https://shorturl.at/jAG46','8 woofer plus 3 tweetor supports Usb/sd/fm/aux/remote with Output - 40w',4)


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE slam_ProductStock (
    prod_id INT REFERENCES slam_Products (Prod_id),
    prod_price NUMERIC(10, 2),
    prod_stock INT,
    prod_mrp INT
);



insert into slam_ProductStock values(1,2108,50,1700);
insert into slam_ProductStock values(2,1984,30,1600);
insert into slam_ProductStock values(3,1610.76,40,1299);

select slp.*,shc.sgst,shc.igst,shc.cgst,shc.gst,sps.prod_price,sps.prod_stock,sps.prod_mrp from 
slam_products slp,slam_Productstock sps,slam_hsn_code shc where slp.prod_id=sps.prod_id and slp.prod_gstc_id=shc.hsn_code

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE slam_regions (
  region_id SERIAL PRIMARY KEY,
  region_name VARCHAR(255) NOT NULL,
  region_pin_from INTEGER NOT NULL,
  region_pin_to INTEGER NOT NULL,
  region_surcharge NUMERIC,
  region_pricewaiver NUMERIC
);
INSERT INTO slam_regions (region_name, region_pin_from, region_pin_to, region_surcharge, region_pricewaiver)
VALUES
  ('Vizag', 530001, 530040, 0, 100), 
  ('Rajmundry',  533101,533205,50 , 10), 
  ('Tadepalligudem', 534101, 534201, 55, 8),  
  ('chennai',  600001,600192, 110, 3), 
  ('Hyderabad', 500001,500091, 75, 5);  



CREATE TABLE slam_cat_serv (
  productcategoryid INT,
  region_id INT,
  PRIMARY KEY (prct_id, region_id)
);

SELECT * from slam_cat_serv
-- Insert sample data
INSERT INTO slam_cat_serv (productcategoryid, region_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 2),
  (2, 3),
  (2, 4),
  (3, 1),
  (3, 5),
  (4, 4),
  (5, 3),
  (6, 1),
  (6, 4),
  (7, 2),
  (8, 3),
  (9, 1),
  (9, 2),
  (9, 3),
  (9, 4),
  (9, 5);

            
