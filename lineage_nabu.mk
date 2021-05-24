#
# Copyright (C) 2018-2021 ArrowOS
#
# SPDX-License-Identifier: Apache-2.0
#

# Inherit common products
$(call inherit-product, $(SRC_TARGET_DIR)/product/core_64_bit.mk)
$(call inherit-product, $(SRC_TARGET_DIR)/product/full_base.mk)

# Inherit device configurations
$(call inherit-product, device/xiaomi/nabu/device.mk)

# Inherit common LineageOS configurations
$(call inherit-product, vendor/lineage/config/common_full_tablet_wifionly.mk)

PRODUCT_CHARACTERISTICS := tablet

PRODUCT_BRAND := Xiaomi
PRODUCT_DEVICE := nabu
PRODUCT_MANUFACTURER := Xiaomi
PRODUCT_MODEL := 21051182G
PRODUCT_NAME := lineage_nabu

PRODUCT_GMS_CLIENTID_BASE := android-xiaomi
