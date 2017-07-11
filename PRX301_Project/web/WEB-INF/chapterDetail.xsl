<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="html" indent="yes" encoding="UTF-8" />

  <xsl:template match="/">
    <xsl:for-each select="chapterPages/chapterPage">
      <div class="manga-page">
        <xsl:attribute name="number">
          <xsl:value-of select="pageNumber" />
        </xsl:attribute>
        <img>
          <xsl:attribute name="src">image<xsl:value-of select="imageUrl" /></xsl:attribute>
        </img>
      </div>
    </xsl:for-each>
  </xsl:template>

</xsl:stylesheet>